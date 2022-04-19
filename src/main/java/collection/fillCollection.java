package collection;

import exceptions.cannotBeEmptyException;
import exceptions.cannotBeNegativeException;
import exceptions.cannotBeNullException;
import exceptions.invalidValueException;
import org.apache.commons.csv.CSVRecord;

/**
 * Provide collection filling from file
 */
public interface fillCollection{

    /**
     *
     * @param records Collection of CSVRecords
     * @throws invalidValueException
     * @throws cannotBeNullException
     * @throws cannotBeNegativeException
     * @throws cannotBeEmptyException
     */
    public void fill(Iterable<CSVRecord> records) throws invalidValueException, cannotBeNullException, cannotBeNegativeException, cannotBeEmptyException;

}
