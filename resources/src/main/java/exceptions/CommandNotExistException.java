package exceptions;

public class CommandNotExistException extends Exception {
    public CommandNotExistException(){
        super("Command doesn't exist");
    }
    public CommandNotExistException(String str){
        super(str);
    }
}
