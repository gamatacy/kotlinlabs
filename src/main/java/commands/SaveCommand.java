package commands;

import collection.CollectionManager;
import fileUtils.FileManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Save collection into csv file, standart output is test.csv
 */
public class SaveCommand extends Command{
    private CollectionManager cManager;
    private FileManager fManager;
    private String path;

    public SaveCommand(CollectionManager cManager, FileManager fManager){
        super("save", " сохранить коллекцию в файл");
        this.cManager = cManager;
        this.fManager = fManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            if(this.path == null) {
                fManager.saveFile("test.csv", this.cManager.getProductsCollection());
            }
            else{
                fManager.saveFile(this.path,this.cManager.getProductsCollection());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.path = arg;
    }
}
