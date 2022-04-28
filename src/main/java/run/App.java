package run;

import fileUtils.FileManager;
import collection.CollectionManager;
import console.ConsoleManager;
import commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        String path;
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager();
        CommandManager commandManager = new CommandManager(
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager),
                new ExitCommand(),
                new UpdateCommand(collectionManager),
                new ClearCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new SaveCommand(collectionManager,fileManager),
                new HeadCommand(collectionManager),
                new RemoveHeadCommand(collectionManager),
                new RemoveAllByManufacturerCommand(collectionManager),
                new FilterByPartNumberCommand(collectionManager),
                new PrintAscendingCommand(collectionManager)
        );

        ConsoleManager console = new ConsoleManager(commandManager, new BufferedReader(new InputStreamReader(System.in)));

        if(args.length == 0){
            path = "test.csv";
        }
        else {
            path = args[0];
        }

        try{
            fileManager.readFile(path);
            collectionManager.fill(fileManager.getFileCollection());
            console.run();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
