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
                new addCommand()
        );
        consoleManager console = new consoleManager(CManager);


        try{

            System.out.println(args[0]);
            fManager.readFile("test2.csv");
            cLM.fill(fManager.getFileCollection());
            fManager.saveFile("test2.csv",cLM.productsCollection);
            console.run();

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
