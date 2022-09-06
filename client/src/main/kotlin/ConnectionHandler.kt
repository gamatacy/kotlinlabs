import commands.ExecutionResult
import commands.ServerRequest
import console.User
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class ConnectionHandler(
    private val host: String,
    private val port: Int
) {
    private lateinit var socket: Socket
    private lateinit var serverInput: ObjectInputStream
    private lateinit var serverOutput: ObjectOutputStream
    private var reconnectAttempts = 0
    private val timeout = 3000

    fun connect(user: User): Boolean{
        while (reconnectAttempts < 3) {
            socket = Socket()
            try {
                println("Trying to connect...")
                socket.connect(InetSocketAddress(host, port),timeout)
                serverOutput = ObjectOutputStream(socket.getOutputStream())
                serverInput = ObjectInputStream(socket.getInputStream())
                println("Connection set!")
                serverOutput.writeObject(user)
                var res = serverInput.readObject() as ExecutionResult
                println(res.message)
                if (!res.result){
                    break
                }
                reconnectAttempts = 0
                return true
            } catch (e: Exception) {
                reconnectAttempts++
                println("Connection failed")
            }
            Thread.sleep(10000)
        }
        return false
    }

    private fun logIn(username: String): User{
        print("Enter password: ")
        val password = readLine()
        return User(username,password)
    }

    fun createRequest(request: ServerRequest): ExecutionResult? {
        serverOutput.writeObject(request)
        return getResponse()
    }

    private fun getResponse(): ExecutionResult? {
        return (serverInput.readObject() as ExecutionResult?)
    }

    fun isConnected(): Boolean {
        return socket.isConnected && !socket.isClosed
    }

}

