package network

import ClientsCommandInvoker
import commands.CommandManager
import commands.ServerRequest
import console.User
import database.UsersHandler
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

        var user = input.readObject() as User
        username = user.username

        try {
            var res = UsersHandler.checkUser(user)
            if (res.result) {
                println("\n$username connected")
                output.writeObject(res)
                while (true) {
                    try {
                        var request = input.readObject() as ServerRequest
                        output.writeObject(ClientsCommandInvoker.invoke(request, commandManager, user))
                    } catch (e: Exception) {
                        println("\n$username disconnected")
                        break
                    }
                }
            } else {
                output.writeObject(res)
            }
        } catch (e: Exception) {

        }

        input.close()
        output.close()
    }

}