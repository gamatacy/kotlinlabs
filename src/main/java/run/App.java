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
        collectionManager cLM = new collectionManager();
        fileManager fManager = new fileManager();
        commandManager CManager = new commandManager(
                new infoCommand(cLM),
                new showCommand(cLM),
                new addCommand(cLM),
                new executeScriptCommand(cLM,fManager),
                new historyCommand(consoleManager.getCommandHistory()),
                new exitCommand(),
                new updateCommand(cLM),
                new clearCommand(cLM),
                new removeByIdCommand(cLM),
                new saveCommand(cLM,fManager),
                new headCommand(cLM),
                new removeHeadCommand(cLM),
                new removeAllByManufacturerCommand(cLM),
                new filterByPartNumberCommand(cLM),
                new printAscendingCommand(cLM)
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
            cLM.fill(fManager.getFileCollection());
            console.run();

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

}
