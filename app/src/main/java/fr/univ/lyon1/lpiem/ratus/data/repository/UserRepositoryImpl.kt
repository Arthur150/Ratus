package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotAlreadyRegisterException
import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotFoundException
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User
import java.lang.IllegalStateException

class UserRepositoryImpl(
    private val dataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getUserWithUID(uid: String, canCreateUser: Boolean): User {
        try {
            return dataSource.getUserWithUID(uid).getOrThrow()
        } catch (ex: UserNotAlreadyRegisterException) {
            if (canCreateUser) {
                return dataSource.createUser(uid).getOrThrow()
            }
        }
        throw UserNotFoundException(uid)
    }

    override suspend fun addTransaction(
        uid: String,
        newTransaction: Transaction,
        newBalance: Double
    ): User {
        return dataSource.addTransaction(uid, newTransaction, newBalance).getOrThrow()
    }

}