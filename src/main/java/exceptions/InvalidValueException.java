package exceptions;

public class InvalidValueException extends Exception{
    public InvalidValueException(){}
    public InvalidValueException(String s) {
        super(s);
    }
}
