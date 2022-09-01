import collection.CollectionManager
import commands.CommandManager
import commands.commandsFiles.*
import database.DatabaseIntoCollection
import network.ServerHandler
import productClasses.ProductBuilder
import serverCommands.ServerAddCommand
import serverCommands.ServerRemoveCommand
import serverCommands.ServerUpdateCommand
import utils.FileManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

fun main() {

    val serverSocket: ServerSocket
    val port: Int = 8080
    val path: String = "collection.csv"
    val collectionManager = CollectionManager()
    val fileManager = FileManager()
    val commandManager = CommandManager()

    commandManager.registerCommands(
        HelpCommand(commandManager.commandsInfo),
        HistoryCommand(),
        ExecuteScriptCommand(commandManager, fileManager),
        InfoCommand(collectionManager),
        ShowCommand(collectionManager),
        ServerAddCommand(collectionManager),
        ExitCommand(),
        ClearCommand(collectionManager),
        SaveCommand(collectionManager, fileManager),
        ServerUpdateCommand(collectionManager),
        RemoveByIdCommand(collectionManager),
        RemoveHeadCommand(collectionManager),
        HeadCommand(collectionManager),
        PrintAscendingCommand(collectionManager),
        RemoveAllByManufacturerCommand(collectionManager),
        FilterByPartNumberCommand(collectionManager),
        ServerRemoveCommand(collectionManager)
    )

    serverSocket = ServerSocket(port)

    val console = Console(commandManager, BufferedReader(InputStreamReader(System.`in`)), System.out, "server")
    val threadHandler = ServerHandler(serverSocket, commandManager)

    try {
        ProductBuilder.newBuilder()
        collectionManager.updateCollection(DatabaseIntoCollection.convert())
        collectionManager.removeFirst()
        //DatabaseIntoCollection.invertConvert(collectionManager.productsCollection)
        //fileManager.readFile(path)
        //collectionManager.fill(fileManager.fileCollection)
    } catch (e: Exception) {
        System.err.println(e.message)
    }

    FunnyPics.printMyself()

    Thread(threadHandler).start()
    println("Waiting for connect")
    console.run()
    serverSocket.close()
    threadHandler.close()

    FunnyPics.printRandom()

}