import collection.CollectionManager
import commands.CommandManager
import commands.ServerRequest
import commands.commandsFiles.*
import console.ConsoleManager
import productClasses.ProductBuilder
import utils.FileManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

fun main() {

    val serverSocket: ServerSocket
    val socket: Socket
    val port: Int = 8080
    var input: ObjectInputStream
    var output: ObjectOutputStream
    var request: ServerRequest

    val path: String = "test2.csv"
    val collectionManager = CollectionManager()
    val fileManager = FileManager()
    val commandManager = CommandManager()
    commandManager.registerCommands(
        HelpCommand(commandManager.commandsInfo),
        HistoryCommand(),
        ExecuteScriptCommand(commandManager, fileManager),
        InfoCommand(collectionManager),
        ShowCommand(collectionManager),
        AddCommand(collectionManager),
        ExitCommand(),
        ClearCommand(collectionManager),
        SaveCommand(collectionManager, fileManager),
        UpdateCommand(collectionManager),
        RemoveByIdCommand(collectionManager),
        RemoveHeadCommand(collectionManager),
        HeadCommand(collectionManager),
        PrintAscendingCommand(collectionManager),
        RemoveAllByManufacturerCommand(collectionManager),
        FilterByPartNumberCommand(collectionManager)
    )

    val console = ConsoleManager(commandManager, BufferedReader(InputStreamReader(System.`in`)), System.out, "server")

    try {
        ProductBuilder.newBuilder()
        fileManager.readFile(path)
        collectionManager.fill(fileManager.fileCollection)
    } catch (e: Exception) {
        System.err.println(e.message)
    } finally {
        console.start()
    }


    println("Waiting for connect")
    serverSocket = ServerSocket(port)
    while (true) {
        ClientsHandler(serverSocket.accept(), commandManager).start()
    }

    serverSocket.close()

}