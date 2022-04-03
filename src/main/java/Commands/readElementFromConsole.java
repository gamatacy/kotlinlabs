package Commands;

import Collection.Classes.Address;
import Collection.Classes.Organization;
import Collection.Classes.Product;
import Console.consoleManager;
import Enums.OrganizationType;
import Enums.UnitOfMeasure;

import java.io.BufferedReader;

public class readElementFromConsole {

    public Product readElement(BufferedReader reader){

        Product product = new Product();

        System.out.print("<user>$ ");

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            setName(reader, product);
            setCoordinates(reader, product);
            setPrice(reader, product);
            setPartNumber(reader, product);
            setManufactureCost(reader, product);
            setUnitOfMeasure(reader, product);
            setOrganization(reader, product);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


        if(consoleManager.getScriptInput()) {
            for (int i = 0; i < 10; i++) {
                System.out.println("<user>$ ");
            }
        }

        return product;
    }

    public void setName(BufferedReader reader,Product product) throws Exception {
        try{
            System.out.print("Enter Name: ");
            product.setName(reader.readLine());
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setName(reader, product);
            }
        }
    }

    public void setCoordinates(BufferedReader reader,Product product) throws Exception {
        try{
            System.out.print("Enter X coordinate (float): ");
            Float x = Float.parseFloat(reader.readLine());
            System.out.print("Enter Y coordinate (float): ");
            float y = Float.parseFloat(reader.readLine());

            product.setCoordinates(x,y);
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setCoordinates(reader, product);
            }
        }
    }

    public void setPrice(BufferedReader reader,Product product) throws Exception {

        try{
            System.out.print("Enter price: ");
            String price = reader.readLine();
            if(price.length() == 0){
                product.setPrice(null);
            }
            else{
                product.setPrice(Float.parseFloat(price));
            }

        }catch(Exception e) {
            if (consoleManager.getScriptInput()) {
                throw new Exception();
            } else {
                System.out.println(e.getMessage());
                setPrice(reader, product);
            }
        }
    }

    public void setPartNumber(BufferedReader reader,Product product) throws Exception {
        try{
            System.out.print("Enter part number: ");
            product.setPartNumber(reader.readLine());
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setPartNumber(reader, product);
            }
        }
    }

    public void setManufactureCost(BufferedReader reader,Product product) throws Exception {
        try{
            System.out.print("Enter manufacture cost: ");
            product.setManufactureCost(Integer.parseInt(reader.readLine()));
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setManufactureCost(reader, product);
            }
        }
    }

    public void setUnitOfMeasure(BufferedReader reader,Product product) throws Exception {
        try{
            System.out.println("Unit of Measure list:  \n-----");

            for(UnitOfMeasure uom : UnitOfMeasure.values()){
                System.out.println(uom.toString());
            }

            System.out.print("----- \nEnter Unit of Measure from  list: ");

            product.setUnitOfMeasure(reader.readLine());
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setUnitOfMeasure(reader, product);
            }
        }
    }

    public void setOrganization(BufferedReader reader,Product product){
        try{

            System.out.print("Enter Organization? y/n: ");

            String exist = reader.readLine();

            if (exist.equalsIgnoreCase("y")){
                Organization organization = new Organization();
                organization.setId(product.getId());
                setOrganizationName(reader,organization);
                setOrganizationFullName(reader,organization);
                setOrganizationType(reader,organization);
                setZipCode(reader,organization);
                organization.setOrganizationExist(true);

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
        }

    }

    public void setOrganizationName(BufferedReader reader,Organization organization) throws Exception {

        try{
            System.out.print("Enter organization name: ");
            organization.setName(reader.readLine());
        }catch (Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setOrganizationName(reader, organization);
            }
        }
    }

    public void setOrganizationFullName(BufferedReader reader,Organization organization) throws Exception {
        try{
            System.out.print("Enter organization full name: ");
            organization.setFullName(reader.readLine());
        }catch (Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setOrganizationFullName(reader, organization);
            }
        }
    }

    public void setOrganizationType(BufferedReader reader,Organization organization) throws Exception {
        try{
            System.out.println("Organization type list:  \n-----");
            for(OrganizationType oType : OrganizationType.values()){
                System.out.println(oType.toString());
            }
            System.out.print("-----\nEnter Organization type from  list: ");
            organization.setType(OrganizationType.equals(reader.readLine()));
        }catch(Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setOrganizationType(reader, organization);
            }
        }
    }

    public void setZipCode(BufferedReader reader,Organization organization) throws Exception {
        try{
            System.out.print("Enter ZipCode: ");
            organization.setOfficialAddress(new Address(reader.readLine()));
        }catch (Exception e){
            if(consoleManager.getScriptInput()) {
                throw new Exception();
            }
            else {
                System.out.println(e.getMessage());
                setZipCode(reader, organization);
            }
        }
    }

}
