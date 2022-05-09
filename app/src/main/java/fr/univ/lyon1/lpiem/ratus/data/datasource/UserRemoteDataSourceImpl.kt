package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotAlreadyRegisterException
import fr.univ.lyon1.lpiem.ratus.data.networking.TransactionNetworking
import fr.univ.lyon1.lpiem.ratus.data.networking.UserNetworking
import fr.univ.lyon1.lpiem.ratus.model.*
import java.lang.IllegalStateException

class UserRemoteDataSourceImpl(
    private val networking: UserNetworking,
    private val transactionNetworking: TransactionNetworking
) : UserRemoteDataSource {

    override suspend fun getUserWithUID(uid: String) : Result<User> {
        return try {
            val document = networking.getUserWithUID(uid)
            val firebaseUser = document.toObject(FirebaseUser::class.java)

            if (firebaseUser != null){
                val transactionsList = ArrayList<Transaction>()
                for (transaction in firebaseUser.transactions){
                    val transactionDocument = transactionNetworking.getTransaction(transaction)
                    val realTransaction = transactionDocument.toObject(Transaction::class.java)
                    if (realTransaction != null) {
                        transactionsList.add(realTransaction)
                    }
                }
                Result.success(firebaseUser.toUser(transactionsList))
            }
            else {

                Result.failure(UserNotAlreadyRegisterException())
            }
        }
        catch (t : Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun createUser(uid: String): Result<User> {
        return try {
            val document = networking.createUser(uid)
            val firebaseUser = document.toObject(FirebaseUser::class.java)

            if (firebaseUser != null){
                val transactionsList = ArrayList<Transaction>()
                for (transaction in firebaseUser.transactions){
                    val transactionDocument = transactionNetworking.getTransaction(transaction)
                    val realTransaction = transactionDocument.toObject(Transaction::class.java)
                    if (realTransaction != null) {
                        transactionsList.add(realTransaction)
                    }
                }
                Result.success(firebaseUser.toUser(transactionsList))
            }
            else {
                Result.failure(IllegalStateException())
            }
        }
        catch (t : Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun addTransaction(uid: String, newTransaction : Transaction, newBalance : Double): Result<User> {
        return try {
            val transactionReference = transactionNetworking.addTransaction(newTransaction)
            val document = networking.addTransaction(uid, transactionReference, newBalance)
            val user = document.toObject(FirebaseUser::class.java)

            if (user != null){
                val transactionsList = ArrayList<Transaction>()
                for (transaction in user.transactions){
                    val transactionDocument = transactionNetworking.getTransaction(transaction)
                    val realTransaction = transactionDocument.toObject(Transaction::class.java)
                    if (realTransaction != null) {
                        transactionsList.add(realTransaction)
                    }
                }
                Result.success(user.toUser(transactionsList))
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