package database

import commands.ExecutionResult
import console.User

class UsersHandler {
    companion object {

        fun checkUser(user: User): ExecutionResult {
            if (!UserDao.isUserExist(user)) {
                UserDao.add(user)
                return ExecutionResult.executionResult(true, "You are registered as ${user.username}")
            }

            return if (UserDao.checkPassword(user.username, user.password)) {
                ExecutionResult.executionResult(true, "You are successfully logged as ${user.username}")
            } else {
                ExecutionResult.executionResult(false, "Wrong password!")
            }

        }

    }
}