package run;

import fileUtils.fileManager;
import Collection.collectionManager;
import Console.consoleManager;
import Commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        String path;
        collectionManager clManager = new collectionManager();
        fileManager fManager = new fileManager();
        commandManager CManager = new commandManager(
                new infoCommand(clManager),
                new showCommand(clManager),
                new addCommand(clManager),
                new executeScriptCommand(clManager,fManager),
                new historyCommand(consoleManager.getCommandHistory()),
                new exitCommand(),
                new updateCommand(clManager),
                new clearCommand(clManager),
                new removeByIdCommand(clManager),
                new saveCommand(clManager,fManager),
                new headCommand(clManager),
                new removeHeadCommand(clManager),
                new removeAllByManufacturerCommand(clManager),
                new filterByPartNumberCommand(clManager),
                new printAscendingCommand(clManager)
        );

        consoleManager console = new consoleManager(CManager, new BufferedReader(new InputStreamReader(System.in)));

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
