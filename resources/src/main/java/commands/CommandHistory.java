package commands;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHistory {
    private List<String> commandHistory;

    public CommandHistory() {
        this.commandHistory = new ArrayList<>();
    }

    public void updateHistory(String command) {
        if(commandHistory.size() == 8){
            commandHistory.remove(0);
        }
        commandHistory.add(command);
    }

    public void printHistory(PrintStream printStream) {
        commandHistory.stream().limit(8).forEach(printStream::println);
    }
}
