package utils;

import exceptions.CommandNotExistException;

import java.io.IOException;

/**
 * Read file by path
 */
public interface ReadFile {
    void readFile(String path) throws IOException, CommandNotExistException;
}
