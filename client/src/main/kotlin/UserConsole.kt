import clientCommands.ClientAddCommand
import clientCommands.ClientExecuteScriptCommand
import clientCommands.ClientRemoveByManCommand
import commands.*
import enums.InputMode
import java.io.BufferedReader
import java.io.PrintStream

/**
 * Read input from user and return commands results
 */
class UserConsole(
    private val commandManager: CommandManager,
    private val reader: BufferedReader,
    private val printStream: PrintStream,
    private var connectionHandler: ConnectionHandler,
    private val username: String
) {

    private val history: CommandHistory = CommandHistory()

    fun run() {
        while (true) {

            printStream.print("<" + username + ">$")

            try {
                var cmd = reader.readLine().split(" ")
                var argument: Any? = null

                var commandName = cmd[0]

                if (commandName.isEmpty()) {
                    continue
                }

                when (commandName) {
                    "add" -> argument = ClientAddCommand.execute(reader,InputMode.USER)
                    "remove_all_by_manufacturer" -> argument = ClientRemoveByManCommand.execute(reader)
                    "execute_script" -> argument = ClientExecuteScriptCommand.execute(
                        reader,
                        cmd[1],
                        connectionHandler,
                        commandManager
                    )
                }

                if (cmd.size > 1) {
                    argument = cmd[1]

                }

                var command = commandManager.getCommand(commandName)

                println(connectionHandler.createRequest(ServerRequest.createRequest(
                    command,
                    argument
                ))?.message)

                /*
                if (executionResult.result) {
                    if (executionResult.message == "Exit success") {
                        break
                    }
                    if (executionResult.message == "history") {
                        history.printHistory(printStream)
                    } else {
                        printStream.println(executionResult.message)
                    }
                    history.updateHistory(command.split(" ").toTypedArray()[0])
                } else {
                    printStream.println(executionResult.message)
                }*/
            } catch (e: Exception) {
                printStream.println(e.message)
            }
        }
        try {
            reader.close()
        } catch (e: Exception) {
            printStream.println(e.message)
        }
    }

}


