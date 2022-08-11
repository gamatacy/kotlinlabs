import commands.ExecutionResult
import commands.ServerRequest
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

    fun getSocket() = socket

    fun connect() {
        try {
            println("Trying to connect...")
            socket.connect(InetSocketAddress(host,port))
            println("Socket opened")
            serverOutput = ObjectOutputStream(socket.getOutputStream())
            serverInput = ObjectInputStream(socket.getInputStream())
            println("Connection set!")
        } catch (e: Exception) {
            System.err.println("DISCONNECTED")
            //e.printStackTrace()
        }
    }

    fun createRequest(request: ServerRequest): ExecutionResult? {
        return try {
            serverOutput.writeObject(request)
            getResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getResponse(): ExecutionResult? {
        return (serverInput.readObject() as ExecutionResult?)
    }

}

