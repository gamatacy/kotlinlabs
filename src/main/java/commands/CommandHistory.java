package commands;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHistory {
    private List<String> commandHistory;

    public CommandHistory() {
        this.commandHistory = new ArrayList<>();
    }

    public void updateHistory(String command) {
        commandHistory.add(command);
    }

    public void printHistory(PrintStream printStream) {
        if (commandHistory.size() < 8) {
            for (int i = 0; i < commandHistory.size();i++) {
                printStream.println(commandHistory.get(i));
            }
        } else {
            for (int i = 0; i < 8; i++) {
                printStream.println(commandHistory.get(i));
            }
        }

    }
}
