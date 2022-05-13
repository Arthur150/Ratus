package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User

interface UserRemoteDataSource {
    suspend fun getUserWithUID(uid: String): Result<User>
    suspend fun createUser(uid: String): Result<User>
    suspend fun addTransaction(
        uid: String,
        newTransaction: Transaction,
        newBalance: Double
    ): Result<User>
}