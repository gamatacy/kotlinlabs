package clientCommands

import enums.InputMode
import productClasses.FieldsReader
import productClasses.Product
import productClasses.ProductBuilder
import java.io.BufferedReader

class ClientAddCommand {
    companion object {
        fun execute(reader: BufferedReader, inputMode: InputMode): Product? {
            try {
                val fieldsReader = FieldsReader(Product::class.java)
                val product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode))
                return product
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}