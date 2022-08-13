import commands.ExecutionResult
import commands.ServerRequest
import console.User
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class ConnectionHandler(
    private val host: String,
    private val port: Int
) {
    private val socket: Socket = Socket()
    private lateinit var serverInput: ObjectInputStream
    private lateinit var serverOutput: ObjectOutputStream
    private var reconnectAttempts = 0

    fun connect(user: User) {
        try {
            println("Trying to connect...")
            socket.connect(InetSocketAddress(host,port))
            serverOutput = ObjectOutputStream(socket.getOutputStream())
            serverInput = ObjectInputStream(socket.getInputStream())
            println("Connection set!")
            serverOutput.writeObject(user)
        } catch (e: Exception) {
            System.err.println("Connection lost, trying to reconnect...")
            if(reconnectAttempts < 3){
                reconnectAttempts++
                connect(user)
            }
            serverOutput.close()
            serverInput.close()
            socket.close()
            System.err.println("Disconnected")
        }
    }

    fun createRequest(request: ServerRequest): ExecutionResult? {
        serverOutput.writeObject(request)
        return getResponse()
    }

    private fun getResponse(): ExecutionResult? {
        return (serverInput.readObject() as ExecutionResult?)
    }

}

