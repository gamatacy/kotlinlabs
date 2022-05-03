package fileUtils;

import productClasses.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;


import java.io.*;
import java.util.Collection;

/**
 * Manager class for interact with files
 */
public class FileManager implements ReadFile {
    private Iterable<CSVRecord> fileCollection;

    //Парсит файл и записывает в коллекцию

    @Override
    public void readFile(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        Reader in = new InputStreamReader(fis);
        this.fileCollection = CSVFormat.EXCEL.parse(in);
        if(in == null){
            try{
                in.close();
                fis.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //Парсит коллекцию обратно в csv
    public void saveFile(String path, Collection collection) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(path), CSVFormat.RFC4180)) {
            Collection<Product> products = collection;
            for (Product product : products) {
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

    public Iterable<CSVRecord> getFileCollection() {
        return fileCollection;
    }

}
