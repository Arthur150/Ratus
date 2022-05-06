package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserNetworking {

    companion object {
        private const val TAG = "GetUserNetworking"
    }

    private val db = Firebase.firestore

    suspend fun getUserWithUID(uid : String): DocumentSnapshot {

        return db.collection("users")
            .document(uid)
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getUserWithUID: ", exception)
            }
            .await()

    }


}