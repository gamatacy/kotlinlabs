import collection.CollectionManager
import commands.CommandManager
import commands.ExecutionResult
import commands.ServerRequest
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ClientsHandler(
    var socket: Socket,
    var commandManager: CommandManager
) : Thread() {

    override fun run() {
        println("Connected")
        var input = ObjectInputStream(socket.getInputStream())
        var output = ObjectOutputStream(socket.getOutputStream())
        while (socket.isConnected) {
            var request = input.readObject() as ServerRequest
            output.writeObject(ServerCommandInvoker.invoke(request, commandManager))
        }
        println("Disconnected")
    }

}