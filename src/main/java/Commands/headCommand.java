package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

public class headCommand extends Command{
    private collectionManager cManager;


    public headCommand(collectionManager cManager){
        super("head");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(cManager.getCollectionFirst());
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
