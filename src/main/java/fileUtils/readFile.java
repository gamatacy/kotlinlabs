package fileUtils;

import java.io.IOException;
import Exceptions.*;

public interface readFile {
    public void readFile(String path) throws IOException, commandNotExistException;
}
