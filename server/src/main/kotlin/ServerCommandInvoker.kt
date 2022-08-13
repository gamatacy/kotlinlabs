import commands.CommandManager
import commands.CommandWithArgument
import commands.ExecutionResult
import commands.ServerRequest
import java.util.ArrayList

class ServerCommandInvoker {
    companion object {
        fun invoke(request: ServerRequest, commandManager: CommandManager): ExecutionResult? {
            val command = commandManager.getCommand(request.command.name)
            if (command is CommandWithArgument) {
                command.setArgument(request.argument as ArrayList<Any>?)
            }
            return command.execute(null)
        }
    }
}