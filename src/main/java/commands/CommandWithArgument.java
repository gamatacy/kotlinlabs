package commands;

public interface CommandWithArgument {
    public void setArgument(String[] args);
    public int getArgumentsCount();
}
