package network

import commands.CommandManager
import java.io.Closeable
import java.net.ServerSocket
import java.net.Socket

class ServerHandler(
    private val serverSocket: ServerSocket,
    private val commandManager: CommandManager
) : Runnable, Closeable {
    private var runFlag = true
    private var socketPool = ArrayList<Socket>()

    override fun run() {
        try {
            var clientsHandler = ClientsHandler(serverSocket, commandManager)
            while (runFlag) {
                socketPool.add(clientsHandler.acceptConnection())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Server closed.")
        }
    }

    override fun close() {
        runFlag = false
        socketPool.forEach { it.close() }
    }

}