import commands.CommandManager
import commands.ServerRequest
import console.User
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RequestHandler(
    private val input : ObjectInputStream,
    private val output : ObjectOutputStream,
    private val commandManager: CommandManager
) : Runnable {
    private var username = "guest"

    override fun run() {
        try {
            username = (input.readObject() as User).username
        }catch (e: Exception){}

        println("$username connected")

        while (true) {
            try {
                var request = input.readObject() as ServerRequest
                output.writeObject(ServerCommandInvoker.invoke(request, commandManager))
            } catch (e: Exception) {
                e.printStackTrace()
                println("$username disconnected")
                break
            }
        }

        input.close()
        output.close()
    }

}