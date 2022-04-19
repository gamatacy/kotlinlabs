package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Print info about the collection
 */
public class infoCommand extends Command{
    private collectionManager cManager;

    public infoCommand(collectionManager CM){
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.cManager = CM;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println("Тип коллекции: " +
                cManager.getProductsCollection().getClass());
        System.out.println("Дата инициализации: " +
                cManager.getCreationDate());
        System.out.println("Количество элементов: " +
                cManager.getCollectionSize());
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
