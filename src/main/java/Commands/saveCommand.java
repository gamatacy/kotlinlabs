package Commands;

import Collection.collectionManager;
import fileUtils.fileManager;

import java.io.BufferedReader;
import java.util.HashMap;

public class saveCommand extends Command{
    private collectionManager cManager;
    private fileManager fManager;
    private String path;

    public saveCommand(collectionManager cManager, fileManager fManager){
        super("save");
        this.cManager = cManager;
        this.fManager = fManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            fManager.saveFile(path, cManager.getProductsCollection());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.path = arg;
    }
}
