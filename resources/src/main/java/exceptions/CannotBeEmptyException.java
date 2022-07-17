package exceptions;

public class CannotBeEmptyException extends Exception {
    public CannotBeEmptyException(){
        super("Cannot be empty");
    }
}
