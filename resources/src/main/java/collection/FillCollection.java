package collection;

import exceptions.CannotBeEmptyException;
import exceptions.CannotBeNegativeException;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;
import org.apache.commons.csv.CSVRecord;

/**
 * Provide collection filling from file
 */
public interface FillCollection {

    /**
     *
     * @param records Collection of CSVRecords
     * @throws InvalidValueException
     * @throws CannotBeNullException
     * @throws CannotBeNegativeException
     * @throws CannotBeEmptyException
     */
     void fill(Iterable<CSVRecord> records) throws InvalidValueException, CannotBeNullException, CannotBeNegativeException, CannotBeEmptyException;

}
