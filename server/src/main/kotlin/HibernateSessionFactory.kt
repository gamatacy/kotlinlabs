import console.User
import productClasses.ProductEntity
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import productClasses.Organization

class HibernateSessionFactory {
    companion object {
        private var sessionFactory: SessionFactory? = null

        fun getSessionFactory(): SessionFactory? {
            if (sessionFactory == null) {
                try {
                    var configuration = Configuration()
                    configuration.configure()
                    configuration.addAnnotatedClass(ProductEntity::class.java)
                    configuration.addAnnotatedClass(Organization::class.java)
                    configuration.addAnnotatedClass(User::class.java)
                    sessionFactory = configuration.buildSessionFactory()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
            return sessionFactory
        }
    }


}