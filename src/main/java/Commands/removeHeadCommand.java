package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

public class removeHeadCommand extends Command{
    private collectionManager cManager;


    public removeHeadCommand(collectionManager cManager){
        super("remove_head");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(cManager.getProductsCollection().pop());
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
