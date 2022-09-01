package serverCommands

import collection.CollectionManager
import commands.Command
import commands.CommandWithArgument
import commands.ExecutionResult
import database.DatabaseIntoCollection
import database.OrganizationDao
import database.ProductDao
import productClasses.ProductEntity
import enums.InputMode
import productClasses.FieldsReader
import productClasses.Product
import productClasses.ProductBuilder
import java.io.BufferedReader

class ServerAddCommand : Command, CommandWithArgument {
    var product: Product? = null
    private var argument: ArrayList<Any>? = null
    private var collectionManager: CollectionManager
    private var inputMode: InputMode = InputMode.USER

    constructor(collectionManager: CollectionManager) : super("add", "добавить новый элемент в коллекцию") {
        this.collectionManager = collectionManager
    }

    override fun execute(reader: BufferedReader?): ExecutionResult {
        if (argument == null) {
            try {
                val fieldsReader = FieldsReader(Product::class.java)
                product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (product != null) {
            var id = ProductBuilder.getBuilder().idGenerator()
            var productEntity = ProductEntity(
                id,
                product!!.name,
                product!!.coordinates.x,
                product!!.coordinates.y,
                product!!.creationDate,
                product!!.price,
                product!!.partNumber,
                product!!.unitOfMeasure,
                product!!.manufactureCost,
                id
            )
            var organization = product!!.manufacturer

            ProductDao.add(productEntity)

            if (organization != null) {
                organization.id = id
                OrganizationDao.add(organization)
            }

            if (productEntity.id?.let { ProductDao.getById(it) } != null) {
                product = null
                argument = null
                inputMode = InputMode.USER
                this.collectionManager.updateCollection(DatabaseIntoCollection.convert())
                return ExecutionResult.executionResult(true, "Element added!")
            }
        }
        product = null
        argument = null
        inputMode = InputMode.USER
        return ExecutionResult.executionResult(false, "Failed to add element!")
    }

    override fun setArgument(argument: ArrayList<Any>?) {
        if (argument != null) {
            if (argument.isNotEmpty()) {
                this.argument = argument
                this.product = argument?.get(0) as Product
            }
        }else{
            this.inputMode = InputMode.SCRIPT
        }
    }

}