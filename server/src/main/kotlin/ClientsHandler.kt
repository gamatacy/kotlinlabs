import commands.CommandManager
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class ClientsHandler(
    private val serverSocket: ServerSocket,
    private val commandManager: CommandManager
) {
    private lateinit var socket: Socket

    fun acceptConnection(): Socket{
        try {
            socket = serverSocket.accept()
            val input = ObjectInputStream(socket.getInputStream())
            val output = ObjectOutputStream(socket.getOutputStream())
            Thread(RequestHandler(input, output, commandManager)).start()
        }catch (e: Exception){
            e.printStackTrace()
        }
        return socket
    }

}