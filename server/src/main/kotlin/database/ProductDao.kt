package database

import productClasses.ProductEntity

class ProductDao {
    companion object {
        fun add(product: ProductEntity) {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            session?.save(product)
            session?.transaction?.commit()
        }

        fun getById(id: Int): ProductEntity? {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var productEntity = session?.get(ProductEntity::class.java, id)
            session?.transaction?.commit()
            return productEntity
        }

        fun deleteById(id: Int) {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var product = session?.get(ProductEntity::class.java, id)
            product?.manufacturerId?.let { OrganizationDao.deleteById(it) }
            session?.delete(product)
            session?.transaction?.commit()
        }
    }
}