package fileUtils;

import java.io.IOException;
import exceptions.*;

/**
 * Read file by path
 */
public interface readFile {
    public void readFile(String path) throws IOException, commandNotExistException;
}
