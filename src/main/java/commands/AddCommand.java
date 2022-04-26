package commands;

import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Add element to collection from user input
 */
public class AddCommand extends Command{
    private CollectionManager cManager;


    public AddCommand(CollectionManager cManager){
        super("add","добавить новый элемент в коллекцию");
        this.cManager = cManager;
    }

    /**
     *
     * @param reader
     */
    @Override
    public void execute(BufferedReader reader){
        ReadElementFromConsole elementReader = new ReadElementFromConsole();
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
