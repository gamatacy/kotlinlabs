import commands.CommandManager
import commands.CommandWithArgument
import commands.ExecutionResult
import commands.ServerRequest

class ServerCommandInvoker {
    companion object {
        fun invoke(request: ServerRequest, commandManager: CommandManager): ExecutionResult? {
            val command = commandManager.getCommand(request.command.name)
            if (command is CommandWithArgument) {
                command.setArgument(request.argument)
            }
            return command.execute(null)
        }
    }
}