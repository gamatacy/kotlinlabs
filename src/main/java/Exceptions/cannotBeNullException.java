package Exceptions;

public class cannotBeNullException extends Exception{
    public  cannotBeNullException(){
        super("Cannot be null");
    }
    public cannotBeNullException(String name){
        super("Cannot be null"+ ' ' + name);
    }
}
