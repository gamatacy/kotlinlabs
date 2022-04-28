package commands;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 * Print last 8 used commands
 */
public class HistoryCommand extends Command {
    private ArrayDeque<String> commandHistory;

    public HistoryCommand(ArrayDeque<String> commandHistory) {
        super("history", "вывести последние 8 команд");
        this.commandHistory = commandHistory;
    }

    @Override
    public void execute(BufferedReader reader) {

        ArrayDeque<String> copyArray = new ArrayDeque<>(commandHistory);

        while (copyArray.size() != 0) {
            System.out.println(copyArray.removeLast());
        }

    }

    @Override
    public void setArgument(String arg) {
    }
}
