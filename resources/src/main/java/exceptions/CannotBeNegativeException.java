package exceptions;

public class CannotBeNegativeException extends Exception{
    public CannotBeNegativeException(){
        super("Cannot be negative");
    }
}
