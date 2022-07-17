package commands;

public interface CommandWithArgument {
    void setArgument(String[] args);
    int getArgumentsCount();
}
