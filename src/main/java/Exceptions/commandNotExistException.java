package Exceptions;

public class commandNotExistException extends Exception {
    public commandNotExistException(){
        super("Command doesn't exist");
    }
}
