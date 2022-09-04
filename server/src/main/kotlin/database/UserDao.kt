package database

import console.User

class UserDao {
    companion object {
        fun add(user: User) {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            session?.save(user)
            session?.transaction?.commit()
        }

        fun checkPassword(username: String, password: String): Boolean {
            var user = getUser(username)
            return user?.password.equals(password)
        }

        fun isUserExist(user: User): Boolean {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var user = getUser(user.username)
            session?.transaction?.commit()
            return user != null
        }

        private fun getUser(username: String): User? {
            val session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            var user = session?.get(User::class.java, username)
            session?.transaction?.commit()
            return user
        }
    }
}