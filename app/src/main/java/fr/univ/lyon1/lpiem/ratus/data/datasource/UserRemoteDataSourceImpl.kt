package fr.univ.lyon1.lpiem.ratus.data.datasource

import android.util.Log
import fr.univ.lyon1.lpiem.ratus.data.networking.GetUserNetworking
import fr.univ.lyon1.lpiem.ratus.model.User
import java.lang.IllegalStateException

class UserRemoteDataSourceImpl(private val networking: GetUserNetworking) : UserRemoteDataSource {

    override suspend fun getUserWithUID(uid: String) : Result<User> {
        return try {
            val document = networking.getUserWithUID(uid)
            val user = document.toObject(User::class.java)

            if (user != null){
                Result.success(user)
            }
            else {
                Result.failure(IllegalStateException())
            }
        }
        catch (t : Throwable) {
            Result.failure(t)
        }
    }

}