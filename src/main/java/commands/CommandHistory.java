package commands;

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

    public void printHistory() {
        if (commandHistory.size() < 8) {
            for (int i = 0; i < commandHistory.size();i++) {
                System.out.println(commandHistory.get(i));
            }
        } else {
            for (int i = 0; i < 8; i++) {
                System.out.println(commandHistory.get(i));
            }
        }

    }
}
