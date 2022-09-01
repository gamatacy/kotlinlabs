import commands.CommandManager
import commands.CommandWithArgument
import commands.ExecutionResult
import commands.ServerRequest
import java.util.ArrayList

class ClientsCommandInvoker {
    companion object {
        @Synchronized fun invoke(request: ServerRequest, commandManager: CommandManager): ExecutionResult? {
            val command = commandManager.getCommand(request.command.name)
            if (command is CommandWithArgument) {
                command.setArgument(request.argument as ArrayList<Any>?)
            }

            var res =  command.execute(null)

            if (res.message == "removed"){
                return commandManager.getCommand("remover").execute(null)
            }

            return res
        }


    }
}