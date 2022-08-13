package commands;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerRequest implements Serializable {
    private Command command;
    private ArrayList<Object> argument;

    private ServerRequest(Command command, ArrayList<Object> argument) {
        this.command = command;
        this.argument = argument;
    }

    public static ServerRequest createRequest(Command command, ArrayList<Object>argument) {
        return new ServerRequest(command, argument);
    }

    public Command getCommand() {
        return command;
    }

    public Object getArgument() {
        return argument;
    }

    public void check() {
        System.out.println(command.getName());
        System.out.println(argument);
    }

}
