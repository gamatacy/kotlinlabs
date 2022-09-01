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
            var scriptLength = 0
            var sucessCount = 0
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
                    scriptLength++

                    if (cmd.size > 1) {
                        argument.add(cmd[1])
                    }

                    when (command.name) {
                        "execute_script" -> throw InvalidValueException("Recursion detected!")
                        "add" -> argument.add(ClientAddCommand.execute(scriptReader, InputMode.SCRIPT))
                        "update" -> argument.add(ClientAddCommand.execute(scriptReader, InputMode.SCRIPT))
                    }

                    var response = connectionHandler.createRequest(ServerRequest.createRequest(command, argument))

                    if (response != null) {
                        if(response.result){
                            sucessCount++
                            println(response.message)
                        }
                    }
                }

            } catch (e: Exception) {}
            when(sucessCount){
                scriptLength -> println("\nScript executed")
                else -> println("\nScript not fully executed")
            }
            return null
        }
    }
}
