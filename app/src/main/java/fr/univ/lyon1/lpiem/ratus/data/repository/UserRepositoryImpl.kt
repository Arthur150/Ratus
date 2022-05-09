package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotAlreadyRegisterException
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User

class UserRepositoryImpl(
    private val dataSource: UserRemoteDataSource
) : UserRepository{
    override suspend fun getUserWithUID(uid: String): User {
        return try {
            dataSource.getUserWithUID(uid).getOrThrow()
        }
        catch (ex : UserNotAlreadyRegisterException) {
            dataSource.createUser(uid).getOrThrow()
        }
    }

    override suspend fun addTransaction(uid: String, newTransaction : Transaction, newBalance : Double): User {
        return dataSource.addTransaction(uid, newTransaction, newBalance).getOrThrow()
    }

}