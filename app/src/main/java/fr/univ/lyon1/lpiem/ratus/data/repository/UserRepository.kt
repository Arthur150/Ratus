package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserWithUID(uid : String) : User
}