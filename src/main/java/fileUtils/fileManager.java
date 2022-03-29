package fileUtils;

import Collection.Classes.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;


import java.io.*;
import java.util.Collection;

public class fileManager implements readFile{
    private Iterable<CSVRecord> fileCollection;

    //Парсит файл и записывает в коллекцию
    @Override
    public void readFile(String path) throws IOException {
        File file = new File(path);
        Reader in = new InputStreamReader(new FileInputStream(file));
        this.fileCollection = CSVFormat.EXCEL.parse(in);
    }

    //Парсит коллекцию обратно в csv
    public void saveFile(String path, Collection collection) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("test2.csv"), CSVFormat.RFC4180)) {
            Collection<Product> copyCol = collection;
            for (Product product : copyCol){
                printer.printRecord(
                        product.getId(),
                        product.getName(),
                        product.getCoordinates().getX(),
                        product.getCoordinates().getY(),
                        product.getCreationDate(),
                        product.getPrice(),
                        product.getPartNumber(),
                        product.getManufactureCost(),
                        product.getUnitOfMeasure(),
                        product.getManufacturer().getId(),
                        product.getManufacturer().getName(),
                        product.getManufacturer().getFullName(),
                        product.getManufacturer().getType(),
                        product.getManufacturer().getOfficialAddress()
                );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Возвращает результат парсинга в виде коллекции какой-то хрени
    public Iterable<CSVRecord> getFileCollection() {
        return fileCollection;
    }

}
