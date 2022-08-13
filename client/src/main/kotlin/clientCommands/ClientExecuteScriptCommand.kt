package clientCommands

import ConnectionHandler
import commands.CommandManager
import commands.CommandWithArgument
import commands.ServerRequest
import enums.InputMode
import exceptions.InvalidValueException
import productClasses.FieldsReader
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ClientExecuteScriptCommand {
    companion object {
        fun execute(
            reader: BufferedReader,
            path: String,
            connectionHandler: ConnectionHandler,
            commandManager: CommandManager
        ): Any? {
            try {
                val file: File = File(path)
                val scriptReader = BufferedReader(FileReader(file))
                while (true) {
                    var cmd = scriptReader.readLine().split(" ")

                    if (cmd.isEmpty()) {
                        continue
                    }

                    var command = commandManager.getCommand(cmd[0])
                    var argument: ArrayList<Any?> = arrayListOf()

                    if (cmd.size > 1){
                        argument.add(cmd[1])
                    }

                    when (command.name) {
                        "execute_script" -> throw InvalidValueException("Recursion detected!")
                        "add" -> argument.add(ClientAddCommand.execute(scriptReader,InputMode.SCRIPT))
                    }

                    connectionHandler.createRequest(ServerRequest.createRequest(command,argument))

                }
            }
            catch (e: Exception) {}
            return null
        }
    }
}
