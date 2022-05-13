package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import kotlinx.coroutines.tasks.await

class TransactionNetworking {

    companion object {
        private const val TAG = "TransactionNetworking"
        private const val COLLECTION_NAME = "transactions"
    }

    private val db = Firebase.firestore

    suspend fun getTransaction(transaction: DocumentReference): DocumentSnapshot {
        return transaction
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getTransaction: ", exception)
            }
            .await()
    }

    suspend fun addTransaction(transaction: Transaction): DocumentReference {
        return db.collection(COLLECTION_NAME)
            .add(transaction.toHashMap())
            .addOnFailureListener {
                Log.e(TAG, "addTransaction: ", it)
            }
            .await()
    }
}