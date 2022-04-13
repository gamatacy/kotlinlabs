package fileUtils;

import java.io.IOException;
import Exceptions.*;

/**
 * Read file by path
 */
public interface readFile {
    public void readFile(String path) throws IOException, commandNotExistException;
}
