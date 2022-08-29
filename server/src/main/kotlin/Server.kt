import collection.CollectionManager
import com.sun.source.tree.TryTree
import commands.CommandManager
import commands.commandsFiles.*
import console.ConsoleManager
import database.DatabaseIntoCollection
import database.ProductDao
import database.ProductEntity
import enums.OrganizationType
import enums.UnitOfMeasure
import network.ServerHandler
import productClasses.Organization
import productClasses.Product
import productClasses.ProductBuilder
import utils.FileManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.util.*

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
    val threadHandler = ServerHandler(serverSocket, commandManager)

    try {
        ProductBuilder.newBuilder()
        collectionManager.updateCollection(DatabaseIntoCollection.convert())
        //fileManager.readFile(path)
        //collectionManager.fill(fileManager.fileCollection)
    } catch (e: Exception) {
        System.err.println(e.message)
    }

    FunnyPics.printMyself()


    /**
    var session = HibernateSessionFactory.getSessionFactory()?.openSession()


    session?.beginTransaction()
    session?.save(ProductEntity(
        1,
        "Name",
        2.1f,
        2.3f,
        Date(),
        555f,
        "333580",
        UnitOfMeasure.METERS,
        135000,
        1
    ))
    session?.save(
        Organization(
            1,
            "Ivan",
            "Corp.",
            OrganizationType.COMMERCIAL,
            "194291"
            )
    )
    session?.transaction?.commit()

    */

    Thread(threadHandler).start()
    println("Waiting for connect")
    console.run()
    serverSocket.close()
    threadHandler.close()

    FunnyPics.printRandom()

}