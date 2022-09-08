import commands.CommandManager
import commands.CommandWithArgument
import commands.ExecutionResult
import commands.ServerRequest
import commands.commandsFiles.ExecuteScriptCommand
import java.util.ArrayList

class ClientsCommandInvoker {
    companion object {
        @Synchronized
        fun invoke(request: ServerRequest, commandManager: CommandManager): ExecutionResult? {
            val command = commandManager.getCommand(request.command.name)
            if (command is CommandWithArgument) {
                command.setArgument(request.argument as ArrayList<Any>?)
            }

            return command.execute(null)
        }
    }
}