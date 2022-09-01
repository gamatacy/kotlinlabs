import collection.CollectionManager
import commands.CommandHistory
import commands.CommandInvoker
import commands.CommandManager
import database.DatabaseIntoCollection
import serverCommands.ServerClearCommand
import serverCommands.ServerRemoveCommand
import java.io.BufferedReader
import java.io.PrintStream

class Console(
    var commandManager: CommandManager,
    var reader: BufferedReader,
    var printStream: PrintStream,
    var username: String
) {
    private var history: CommandHistory = CommandHistory()

    fun run() {
        while (true) {
            printStream.print("<$username>$")
            try {
                var command = reader.readLine()

                if (command.isEmpty()) {
                    continue
                }

                var executionResult =
                    CommandInvoker.invokeUserCommand(command.split(" ").toTypedArray(), reader, commandManager)

                when (executionResult.message) {
                    "Exit success" -> println(executionResult.message)
                    "removed" -> {
                        commandManager.getCommand("remover").execute(null)
                        continue
                    }
                    "Collection cleared" -> {
                        ServerClearCommand.execute()
                        continue
                    }
                    else -> {
                        history.updateHistory(command.split(" ").toTypedArray()[0])
                        println(executionResult.message)
                        continue
                    }
                }

                break

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}