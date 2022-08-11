package commands;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private Command command;
    private Object argument;

    private ServerRequest(Command command, Object argument) {
        this.command = command;
        this.argument = argument;
    }

    public static ServerRequest createRequest(Command command, Object argument) {
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
