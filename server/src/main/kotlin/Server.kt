import collection.CollectionManager
import commands.CommandManager
import commands.commandsFiles.*
import console.ConsoleManager
import productClasses.ProductBuilder
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

    serverSocket = ServerSocket(port)

    val console = ConsoleManager(commandManager, BufferedReader(InputStreamReader(System.`in`)), System.out, "server")
    val threadHandler = ServerHandler(serverSocket,commandManager)

    try {
        ProductBuilder.newBuilder()
        fileManager.readFile(path)
        collectionManager.fill(fileManager.fileCollection)
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