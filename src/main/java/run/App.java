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
        CollectionManager clManager = new CollectionManager();
        FileManager fManager = new FileManager();
        CommandManager CManager = new CommandManager(
                new InfoCommand(clManager),
                new ShowCommand(clManager),
                new AddCommand(clManager),
                new ExecuteScriptCommand(clManager,fManager),
                new HistoryCommand(ConsoleManager.getCommandHistory()),
                new ExitCommand(),
                new UpdateCommand(clManager),
                new ClearCommand(clManager),
                new RemoveByIdCommand(clManager),
                new SaveCommand(clManager,fManager),
                new HeadCommand(clManager),
                new RemoveHeadCommand(clManager),
                new RemoveAllByManufacturerCommand(clManager),
                new FilterByPartNumberCommand(clManager),
                new PrintAscendingCommand(clManager)
        );

        ConsoleManager console = new ConsoleManager(CManager, new BufferedReader(new InputStreamReader(System.in)));

        if(args.length == 0){
            path = "test.csv";
        }
        else {
            path = args[0];
        }

        try{

            fManager.readFile(path);
            clManager.fill(fManager.getFileCollection());
            console.run();

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

}
