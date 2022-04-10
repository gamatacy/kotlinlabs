package Commands;

import Collection.Classes.Address;
import Collection.Classes.Organization;
import Collection.Classes.Product;
import Collection.collectionManager;
import Console.consoleManager;
import Enums.OrganizationType;
import Enums.UnitOfMeasure;

import java.lang.NumberFormatException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.function.BiFunction;

public class addCommand extends Command{
    private collectionManager cManager;


    public addCommand(collectionManager cManager){
        super("add","добавить новый элемент в коллекцию");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader){
        readElementFromConsole elementReader = new readElementFromConsole();
        cManager.addToCollectionLast(elementReader.readElement(reader));
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {}

}
