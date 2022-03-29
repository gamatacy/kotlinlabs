package Commands;

import Collection.Classes.Organization;
import Collection.Classes.Product;
import Collection.collectionManager;
import Enums.OrganizationType;
import Enums.UnitOfMeasure;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class addCommand extends Command{
    private collectionManager cManager;


    public addCommand(){
        super("add");

    }

    @Override
    public void execute() {
        addElement();
    }

    public void addElement(){
        Product product = new Product();
        System.out.print("<user>$ ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        setName(reader,product);
        setCoordinates(reader,product);
        setPrice(reader,product);
        setPartNumber(reader,product);
        setManufactureCost(reader,product);
        setUnitOfMeasure(reader,product);
        setOrganization(reader,product);

        System.out.println(product.toString());

    }

    public void setName(BufferedReader reader,Product product){
        try{
            System.out.print("Enter Name: ");
            product.setName(reader.readLine());
        }catch(Exception e){
            System.out.println(e.getMessage());
            setName(reader,product);
        }
    }

    public void setCoordinates(BufferedReader reader,Product product){
        try{

            System.out.print("Enter X coordinate: ");
            Float x = Float.parseFloat(reader.readLine());
            System.out.print("Enter Y coordinate: ");
            float y = Float.parseFloat(reader.readLine());

            product.setCoordinates(x,y);

        }catch(Exception e){
            System.out.println(e.getMessage());
            setCoordinates(reader,product);
        }
    }

    public void setPrice(BufferedReader reader,Product product){
        try{
            System.out.print("Enter price: ");

            product.setPrice(Float.parseFloat(reader.readLine()));

        }catch(Exception e){
            System.out.println(e.getMessage());
            setPrice(reader,product);
        }
    }

    public void setPartNumber(BufferedReader reader,Product product){
        try{
            System.out.print("Enter part number: ");

            product.setPartNumber(reader.readLine());

        }catch(Exception e){
            System.out.println(e.getMessage());
            setPartNumber(reader,product);
        }
    }

    public void setManufactureCost(BufferedReader reader,Product product){
        try{

            System.out.print("Enter manufacture cost: ");

            product.setManufactureCost(Integer.parseInt(reader.readLine()));

        }catch(Exception e){
            System.out.println(e.getMessage());
            setManufactureCost(reader,product);
        }
    }

    public void setUnitOfMeasure(BufferedReader reader,Product product){
        try{

            System.out.println("Unit of Measure list:  ");

            for(UnitOfMeasure uom : UnitOfMeasure.values()){
                System.out.println(uom.toString());
            }

            System.out.print("Enter Unit of Measure from  list: ");
            product.setUnitOfMeasure(reader.readLine());

        }catch(Exception e){
            System.out.println(e.getMessage());
            setUnitOfMeasure(reader,product);
        }
    }

    public void setOrganization(BufferedReader reader,Product product){
        try{

            System.out.print("Enter Organization? y/n: ");

            String exist = reader.readLine();

            if (exist.equalsIgnoreCase("y")){
                Organization organization = new Organization(
                        product.getId(),
                        setOrganizationName(reader),
                        setOrganizationFullName(reader),
                        setOrganizationType(reader),
                        setZipCode(reader)
                );
                product.setOrganization(organization);
            }
            else if(exist.equalsIgnoreCase("n")){
                Organization organization = new Organization();
                product.setOrganization(organization);
            }
            else{
                setOrganization(reader,product);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            setOrganization(reader,product);
        }

    }

    public String setOrganizationName(BufferedReader reader){

        try{

            System.out.print("Enter organization name: ");

            return reader.readLine();
        }catch (Exception e){
            System.out.println(e.getMessage());
            setOrganizationName(reader);
        }
        return null;
    }

    public String setOrganizationFullName(BufferedReader reader){

        try{

            System.out.print("Enter organization full name: ");

            return reader.readLine();
        }catch (Exception e){
            System.out.println(e.getMessage());
            setOrganizationFullName(reader);
        }
        return null;
    }

    public OrganizationType setOrganizationType(BufferedReader reader){
        try{

            System.out.println("Organization type list:  ");

            for(OrganizationType oType : OrganizationType.values()){
                System.out.println(oType.toString());
            }

            System.out.print("Enter Organization type from  list: ");

            String type = reader.readLine();

            if(OrganizationType.equals(type) == null){
                setOrganizationType(reader);
            }
            else{
                return OrganizationType.equals(type);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            setOrganizationType(reader);
        }
        return null;
    }

    public String setZipCode(BufferedReader reader){
        try{

            System.out.print("Enter ZipCode: ");

            return reader.readLine();

        }catch (Exception e){
            System.out.println(e.getMessage());
            setZipCode(reader);
        }
        return null;
    }

}
