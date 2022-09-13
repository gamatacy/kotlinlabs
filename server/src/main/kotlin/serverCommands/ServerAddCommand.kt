package serverCommands

import collection.CollectionManager
import commands.Command
import commands.CommandWithArgument
import commands.ExecutionResult
import console.User
import database.DatabaseIntoCollection
import database.OrganizationDao
import database.ProductDao
import database.UserDao
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
    private var user: User? = null;

    constructor(collectionManager: CollectionManager) : super("add", "add new element") {
        this.collectionManager = collectionManager
    }

    override fun execute(reader: BufferedReader?): ExecutionResult {
        if (argument == null) {
            try {
                val fieldsReader = FieldsReader(Product::class.java)
                product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode))
                ProductBuilder.getBuilder().removeId(product!!.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (user != null) {
            if (!UserDao.isUserExist(user!!)) {
                UserDao.add(user!!)
            }
        }

        if (product != null) {
            this.product!!.id = ProductBuilder.getBuilder().idGenerator()
            var productEntity = ProductEntity(
                product!!.id,
                product!!.name,
                product!!.coordinates.x,
                product!!.coordinates.y,
                product!!.creationDate,
                product!!.price,
                product!!.partNumber,
                product!!.unitOfMeasure,
                product!!.manufactureCost,
                product!!.id,
                user
            )
            var organization = product!!.manufacturer

            ProductDao.add(productEntity)

            if (organization != null) {
                organization.id = product!!.id
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
                this.product = argument[0] as Product
                this.user = argument[1] as User
            }
        } else {
            this.inputMode = InputMode.SCRIPT
        }
    }

}