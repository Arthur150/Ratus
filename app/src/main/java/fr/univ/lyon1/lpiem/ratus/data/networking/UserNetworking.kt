package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.tasks.await

class UserNetworking {

    companion object {
        private const val TAG = "GetUserNetworking"
        private const val COLLECTION_NAME = "users"
    }

    private val db = Firebase.firestore

    suspend fun getUserWithUID(uid : String): DocumentSnapshot {

        return db.collection(COLLECTION_NAME)
            .document(uid)
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getUserWithUID: ", exception)
            }
            .await()

    }

    suspend fun createUser(uid: String): DocumentSnapshot {
        db.collection(COLLECTION_NAME)
            .document(uid)
            .set(User(uid = uid).toHashMap())
            .addOnFailureListener { exception ->
                Log.e(TAG, "createUser: ", exception)
            }
            .await()
        return getUserWithUID(uid)
    }

    suspend fun addTransaction(uid: String, transaction : DocumentReference, balance : Double) : DocumentSnapshot {
        db.collection(COLLECTION_NAME)
            .document(uid)
            .update(hashMapOf<String, Any>(
                "balance" to balance,
                "transactions" to FieldValue.arrayUnion(transaction)
            ))
            .addOnFailureListener { exception ->
                Log.e(TAG, "addTransaction: ", exception)
            }
            .await()
        return getUserWithUID(uid)
    }


}