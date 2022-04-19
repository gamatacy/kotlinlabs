package Exceptions;

public class commandNotExistException extends Exception {
    public commandNotExistException(){
        super("Command doesn't exist");
    }
    public commandNotExistException(String str){
        super(str);
    }
}
