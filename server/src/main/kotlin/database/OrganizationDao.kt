package database

import productClasses.Organization

class OrganizationDao {
    companion object{
        fun getById(id: Int): Organization? {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var organization= session?.get(Organization::class.java, id)
            session?.transaction?.commit()
            return organization
        }

        fun deleteById(id: Int) {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var organization= session?.get(Organization::class.java, id)
            session?.delete(organization)
            session?.transaction?.commit()
        }
    }
}