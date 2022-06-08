package utils;

import java.io.IOException;
import exceptions.*;

/**
 * Read file by path
 */
public interface ReadFile {
    void readFile(String path) throws IOException, CommandNotExistException;
}
