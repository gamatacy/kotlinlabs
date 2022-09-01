package database

import java.util.ArrayDeque
import productClasses.Coordinates
import productClasses.Product
import productClasses.ProductBuilder
import productClasses.ProductEntity

class DatabaseIntoCollection {
    companion object {
        fun convert(): ArrayDeque<Product> {
            var session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var list = session?.createQuery("from ProductEntity")?.list()
            var deque = ArrayDeque<Product>(list?.size!!)

            if (list != null) {
                for (entity in list){
                    var product = entity as ProductEntity
                    val organization = product.manufacturerId?.let { OrganizationDao.getById(it) }
                    ProductBuilder.getBuilder().addId(product.id)

                    deque.add(ProductBuilder.getBuilder().build(
                        product.id,
                        product.name,
                        product.y?.let { Coordinates(product.x, it) },
                        product.creationDate,
                        product.price,
                        product.partNumber,
                        product.manufactureCost!!,
                        product.unitOfMeasure,
                        organization
                    ))

                }
            }

            session?.transaction?.commit()
            return deque
        }

        fun invertConvert(deque: ArrayDeque<Product>){
            var session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            session?.createQuery("DELETE FROM Organization")?.executeUpdate()
            session?.createQuery("DELETE FROM ProductEntity")?.executeUpdate()
            session?.transaction?.commit()

            for (product in deque){
                var productEntity = ProductEntity(
                    product.id!!,
                    product!!.name,
                    product!!.coordinates.x,
                    product!!.coordinates.y,
                    product!!.creationDate,
                    product!!.price,
                    product!!.partNumber,
                    product!!.unitOfMeasure,
                    product!!.manufactureCost,
                    product.id!!
                )

                ProductDao.add(productEntity)

                OrganizationDao.add(product.manufacturer)
            }

        }

    }
}