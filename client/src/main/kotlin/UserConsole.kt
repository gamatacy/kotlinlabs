import clientCommands.ClientAddCommand
import clientCommands.ClientExecuteScriptCommand
import clientCommands.ClientRemoveByManCommand
import commands.*
import enums.InputMode
import java.io.BufferedReader
import java.io.PrintStream

/**
 * Read input from user
 */
class UserConsole(
    private val commandManager: CommandManager,
    private val reader: BufferedReader,
    private val printStream: PrintStream,
    private var connectionHandler: ConnectionHandler,
    private var username: String? = "user"
) {

    private val history: CommandHistory = CommandHistory()

    fun run() {
        while (true) {

            printStream.print("<" + username + ">$")

            try {
                var cmd = reader.readLine().split(" ")
                var argument: ArrayList<Any?> = arrayListOf()

                var commandName = cmd[0]

                if (commandName.isEmpty()) {
                    continue
                }

                if (cmd.size > 1) {
                    argument.add( cmd[1])

                }

                when (commandName) {
                    "add" -> argument.add(ClientAddCommand.execute(reader, InputMode.USER))
                    "update" -> argument.add(ClientAddCommand.execute(reader, InputMode.USER))
                    "remove_all_by_manufacturer" -> argument.add(ClientRemoveByManCommand.execute(reader))
                    "execute_script" -> argument.add(ClientExecuteScriptCommand.execute(
                        reader,
                        cmd[1],
                        connectionHandler,
                        commandManager
                    ))
                }



                var command = commandManager.getCommand(commandName)

                println(
                    connectionHandler.createRequest(
                        ServerRequest.createRequest(
                            command,
                            argument
                        )
                    )?.message
                )

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


