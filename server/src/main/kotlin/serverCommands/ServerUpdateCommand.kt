package serverCommands

import collection.CollectionManager
import commands.Command
import commands.CommandWithArgument
import commands.ExecutionResult
import console.User
import database.ProductDao
import enums.InputMode
import productClasses.FieldsReader
import productClasses.Product
import productClasses.ProductBuilder
import java.io.BufferedReader

class ServerUpdateCommand : Command, CommandWithArgument {
    private var collectionManager: CollectionManager
    private var product: Product? = null
    private var id: Int? = null
    private var argument: ArrayList<Any>? = null
    private var user: User? = null

    constructor(collectionManager: CollectionManager) : super(
        "update",
        "обновить значение элемента коллекции, id которого равен заданному"
    ) {
        this.collectionManager = collectionManager
    }

    override fun execute(reader: BufferedReader?): ExecutionResult {
        if(argument == null) {
            return ExecutionResult.executionResult(false, "Element not updated!")
        }
        if(!ProductBuilder.getBuilder().checkId(id)){
            return ExecutionResult.executionResult(false, "Element with this id doesn't exist!")
        }
        if(!ProductDao.getById(this.id!!)?.owner?.username.equals(this.user?.username)){
            return ExecutionResult.executionResult(false, "You do not have access to this product!")
        }


        ProductDao.deleteById(id!!)
        ProductBuilder.getBuilder().removeId(id)
        if (product == null) {
            try {
                val fieldsReader = FieldsReader(Product::class.java)
                product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, InputMode.USER))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        product?.id = id
        product?.manufacturer?.id == id

        var array = ArrayList<Any>(1)
        product?.let { array.add(it) }
        user?.let { array.add(it) }
        var add = ServerAddCommand(collectionManager)
        add.setArgument(array)
        var res = add.execute(null)

        return if (res.result) {
            ExecutionResult.executionResult(true, "Element updated!")
        } else {
            ExecutionResult.executionResult(true, "Element not updated!")
        }
    }

    override fun setArgument(argument: ArrayList<Any>?) {
        this.user = null
        this.argument = null
        this.product = null
        this.id = null
        try{
        if (argument != null) {
            if (argument.isNotEmpty()) {
                this.argument = argument
                this.id = Integer.valueOf(argument[0] as String)
                this.product = argument[1] as Product
                if (argument.size > 2) {
                    this.user = argument[2] as User
                }
            }
        }
        }catch (e: Exception){}
    }

}