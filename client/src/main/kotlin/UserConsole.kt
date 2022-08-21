import clientCommands.ClientAddCommand
import clientCommands.ClientExecuteScriptCommand
import clientCommands.ClientRemoveByManCommand
import commands.*
import console.User
import enums.InputMode
import java.io.BufferedReader
import java.io.PrintStream
import java.net.SocketException

/**
 * Read input from user
 */
class UserConsole(
    private val commandManager: CommandManager,
    private val reader: BufferedReader,
    private val printStream: PrintStream,
    private var connectionHandler: ConnectionHandler,
    private val user: User
) {
    private val username = user.username
    private val history: CommandHistory = CommandHistory()

    fun run() {
        while (connectionHandler.isConnected()) {

            printStream.print("<$username>$")

            try {
                var cmd = reader.readLine().split(" ")
                var argument: ArrayList<Any?> = arrayListOf()

                var commandName = cmd[0]

                if (commandName.isEmpty()) {
                    continue
                }

                if (cmd.size > 1) {
                    argument.add(cmd[1])

                }

                when (commandName) {
                    "exit" -> break
                    "history" -> history.printHistory(System.out)
                    "add" -> argument.add(ClientAddCommand.execute(reader, InputMode.USER))
                    "update" -> argument.add(ClientAddCommand.execute(reader, InputMode.USER))
                    "remove_all_by_manufacturer" -> argument.add(ClientRemoveByManCommand.execute(reader))
                    "execute_script" ->{
                        ClientExecuteScriptCommand.execute(
                            reader,
                            cmd[1],
                            connectionHandler,
                            commandManager
                        )
                        argument[0] = null
                    }

                }

                var command = commandManager.getCommand(commandName)

                var response = connectionHandler.createRequest(
                    ServerRequest.createRequest(
                        command,
                        argument
                    )
                )

                if (response != null) {
                    if (response.result && commandName != "history") {
                        history.updateHistory(commandName)
                    }
                    println(response.message)
                }

            } catch (se: SocketException) {
                println("Connection lost")
                connectionHandler.connect(user)
            } catch (e: Exception) {
                println(e.message)
            }
        }
        try {
            println("Exit success")
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}


