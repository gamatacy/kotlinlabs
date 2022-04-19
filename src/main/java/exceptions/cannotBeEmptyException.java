package exceptions;

public class cannotBeEmptyException extends Exception {
    public cannotBeEmptyException(){
        super("Cannot be empty");
    }
}
