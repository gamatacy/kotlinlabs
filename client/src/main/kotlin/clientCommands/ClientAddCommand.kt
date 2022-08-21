package clientCommands

import enums.InputMode
import productClasses.FieldsReader
import productClasses.Product
import productClasses.ProductBuilder
import java.io.BufferedReader

class ClientAddCommand {
    companion object {
        fun execute(reader: BufferedReader, inputMode: InputMode): Product? {
            val fieldsReader = FieldsReader(Product::class.java)
            return ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode))
        }
    }
}