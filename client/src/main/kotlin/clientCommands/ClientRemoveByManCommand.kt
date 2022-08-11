package clientCommands

import enums.InputMode
import productClasses.FieldsReader
import productClasses.Organization
import productClasses.Product
import productClasses.ProductBuilder
import java.io.BufferedReader

class ClientRemoveByManCommand {
    companion object {
        fun execute(reader: BufferedReader): Product {
            val fieldsReader = FieldsReader(Organization::class.java)
            return ProductBuilder.getBuilder().buildManufacturer(fieldsReader.read(reader, InputMode.USER))
        }
    }
}