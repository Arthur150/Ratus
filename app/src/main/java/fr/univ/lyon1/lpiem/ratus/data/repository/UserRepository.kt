package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User

interface UserRepository {
    suspend fun getUserWithUID(uid : String) : User
    suspend fun addTransaction(uid: String, newTransaction : Transaction, newBalance : Double) : User
}