package serverCommands

class ServerClearCommand {
    companion object{
        //Delete all data from database
        fun execute(){
            var session = HibernateSessionFactory.getSessionFactory()?.openSession()
            session?.beginTransaction()
            session?.createQuery("DELETE FROM Organization")?.executeUpdate()
            session?.createQuery("DELETE FROM ProductEntity")?.executeUpdate()
            session?.transaction?.commit()
        }
    }
}