import commands.CommandManager
import commands.ServerRequest
import commands.commandsFiles.*
import console.User
import productClasses.ProductBuilder
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val host: String = "localhost"
    val port: Int = 8080

    val connectionHandler: ConnectionHandler = ConnectionHandler(host,port)

    val commandManager = CommandManager()

    commandManager.registerCommands(
        AddCommand(null),
        RemoveAllByManufacturerCommand(null),
        ClearCommand(null),
        ExecuteScriptCommand(null,null),
        ExitCommand(),
        FilterByPartNumberCommand(null),
        HeadCommand(null),
        HelpCommand(null),
        HistoryCommand(),
        InfoCommand(null),
        PrintAscendingCommand(null),
        RemoveAllByManufacturerCommand(null),
        RemoveByIdCommand(null),
        RemoveHeadCommand(null),
        ShowCommand(null),
        UpdateCommand(null)
    )

    print("Enter username: ")
    val user = User(readLine())

    connectionHandler.connect(user)

    val console = UserConsole(commandManager,
        BufferedReader(InputStreamReader(System.`in`)),
        System.out,
        connectionHandler,
        user.username)

    ProductBuilder.newBuilder()

    console.run()

}