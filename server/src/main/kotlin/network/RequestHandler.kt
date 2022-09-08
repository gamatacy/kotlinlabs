package network

import ClientsCommandInvoker
import commands.CommandManager
import commands.ServerRequest
import console.User
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.SocketException

class RequestHandler(
    private val input: ObjectInputStream,
    private val output: ObjectOutputStream,
    private val commandManager: CommandManager
) : Runnable {
    private var username = "guest"

    override fun run() {
        try {
            username = (input.readObject() as User).username
        } catch (e: Exception) {
            e.printStackTrace()
        }

        println("\n$username connected")

        while (true) {
            try {
                var request = input.readObject() as ServerRequest
                output.writeObject(ClientsCommandInvoker.invoke(request, commandManager))
            } catch (e: Exception) {
                println("\n$username disconnected")
                break
            }
        }

        input.close()
        output.close()
    }

}