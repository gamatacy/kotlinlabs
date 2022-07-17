package exceptions;

public class CannotBeNullException extends Exception{
    public CannotBeNullException(){
        super("Cannot be null");
    }
    public CannotBeNullException(String name){
        super("Cannot be null"+ ' ' + name);
    }
}
