package serverCommands

import collection.CollectionManager
import commands.Command
import commands.ExecutionResult
import database.DatabaseIntoCollection
import java.io.BufferedReader

class ServerRemoveCommand: Command{
    private var collectionManager: CollectionManager

    constructor(collectionManager: CollectionManager): super("remover","^_^"){
        this.collectionManager = collectionManager
    }

    override fun execute(reader: BufferedReader?): ExecutionResult {
        DatabaseIntoCollection.invertConvert(collectionManager.productsCollection)
        return ExecutionResult.executionResult(true,"Element(s) removed")
    }

}