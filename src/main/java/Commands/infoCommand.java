package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

public class infoCommand extends Command{
    private collectionManager cManager;

    public infoCommand(collectionManager CM){
        super("info");
        this.cManager = CM;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(
                cManager.getProductsCollection().getClass()
        );
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
