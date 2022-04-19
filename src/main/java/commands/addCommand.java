package commands;

import collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Add element to collection from user input
 */
public class addCommand extends Command{
    private collectionManager cManager;


    public addCommand(collectionManager cManager){
        super("add","добавить новый элемент в коллекцию");
        this.cManager = cManager;
    }

    /**
     *
     * @param reader
     */
    @Override
    public void execute(BufferedReader reader){
        readElementFromConsole elementReader = new readElementFromConsole();
        cManager.addToCollectionLast(elementReader.readElement(reader));
    }

    /**
     *
     * @param arg
     * @param commands
     */
    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {}

}
