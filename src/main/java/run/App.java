package run;

import com.opencsv.bean.function.AccessorInvoker;
import commands.commandsFiles.*;
import fileUtils.FileManager;
import collection.CollectionManager;
import console.ConsoleManager;
import commands.*;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Паттерны
 * Переделать команд менеджер
 * Переделать историю команд
 * Убрать сет аргумент
 * Билдер паттерн
 *
 *
 * */



public class App {
    public static void main(String[] args) {
        String path;
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager();
        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands(
                new HelpCommand(commandManager.getCommandsInfo()),
                new HistoryCommand(),
                new ExecuteScriptCommand(commandManager,fileManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager),
                new ExitCommand(),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager,fileManager),
                new UpdateCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new RemoveHeadCommand(collectionManager),
                new HeadCommand(collectionManager),
                new PrintAscendingCommand(collectionManager),
                new RemoveAllByManufacturerCommand(collectionManager),
                new FilterByPartNumberCommand(collectionManager)
        );

        ConsoleManager console = new ConsoleManager(commandManager, new BufferedReader(new InputStreamReader(System.in)));

        if(args.length == 0){
            path = "test.csv";
        }
        else {
            path = args[0];
        }

        try{
            ProductBuilder.newBuilder();
            fileManager.readFile(path);
            collectionManager.fill(fileManager.getFileCollection());
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            console.run();
        }

    }
}
