import collection.CollectionManager
import commands.CommandManager
import commands.ExecutionResult
import commands.ServerRequest
import console.User
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ClientsHandler(
    var socket: Socket,
    var commandManager: CommandManager
) : Thread() {

    override fun run() {
        var input = ObjectInputStream(socket.getInputStream())
        var output = ObjectOutputStream(socket.getOutputStream())

        var username = (input.readObject() as User).username

        println("\n$username connected\n")

        try {
            while (socket.isConnected) {
                var request = input.readObject() as ServerRequest
                output.writeObject(ServerCommandInvoker.invoke(request, commandManager))
            }
        }catch (e: Exception){
            println("\n$username disconnected\n")
        }
        input.close()
        output.close()
        socket.close()
    }

}