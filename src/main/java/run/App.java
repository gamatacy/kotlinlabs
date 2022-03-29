package run;

import fileUtils.fileManager;
import Collection.collectionManager;
import Console.consoleManager;
import Commands.*;

public class App {
    public static void main(String[] args) {
        collectionManager cLM = new collectionManager();
        fileManager fManager = new fileManager();
        commandManager CManager = new commandManager(
                new infoCommand(cLM),
                new showCommand(cLM),
                new addCommand(cLM),
                new executeScriptCommand(cLM,fManager),
                new historyCommand(consoleManager.getCommandHistory()),
                new exitCommand()
        );
        consoleManager console = new consoleManager(CManager);


        try{

            fManager.readFile("test.csv");
            cLM.fill(fManager.getFileCollection());
            console.run();
            fManager.saveFile("test2.csv",cLM.getProductsCollection());

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
