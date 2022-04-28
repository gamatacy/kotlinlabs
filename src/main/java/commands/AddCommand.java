package commands;

import collection.CollectionManager;

import java.io.BufferedReader;

/**
 * Add element to collection from user input
 */
public class AddCommand extends Command{
    private final CollectionManager collectionManager;


    public AddCommand(CollectionManager collectionManager){
        super("add","добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     *
     * @param reader
     */
    @Override
    public void execute(BufferedReader reader){
        ReadElementFromConsole elementReader = new ReadElementFromConsole();
        collectionManager.addToCollectionLast(elementReader.readElement(reader));
    }

    /**
     *  @param arg
     *
     */
    @Override
    public void setArgument(String arg) {}

}
