package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.model.User

interface UserRemoteDataSource {
    suspend fun getUserWithUID(uid : String) : Result<User>
}