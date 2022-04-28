package commands;

import collection.CollectionManager;

import java.io.BufferedReader;

/**
 * Print info about the collection
 */
public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println("Тип коллекции: " +
                collectionManager.getProductsCollection().getClass());
        System.out.println("Дата инициализации: " +
                collectionManager.getCreationDate());
        System.out.println("Количество элементов: " +
                collectionManager.getCollectionSize());
    }

    @Override
    public void setArgument(String arg) {

    }
}
