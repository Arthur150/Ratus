package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val dataSource: UserRemoteDataSource
) : UserRepository{
    override suspend fun getUserWithUID(uid: String): User {
        return dataSource.getUserWithUID(uid).getOrThrow()
    }

}