package commands;

import collection.CollectionManager;
import fileUtils.FileManager;

import java.io.BufferedReader;

/**
 * Save collection into csv file, standart output is test.csv
 */
public class SaveCommand extends Command{
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private String path;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager){
        super("save", " сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            if(this.path == null) {
                fileManager.saveFile("test.csv", this.collectionManager.getProductsCollection());
            }
            else{
                fileManager.saveFile(this.path,this.collectionManager.getProductsCollection());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg) {
        this.path = arg;
    }
}
