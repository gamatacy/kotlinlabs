package Collection;

import Collection.Classes.Product;
import Exceptions.cannotBeEmptyException;
import Exceptions.cannotBeNegativeException;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;
import fileUtils.fileManager;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.util.ArrayDeque;

public interface fillCollection{

    public void fill(Iterable<CSVRecord> records) throws invalidValueException, cannotBeNullException, cannotBeNegativeException, cannotBeEmptyException;

}
