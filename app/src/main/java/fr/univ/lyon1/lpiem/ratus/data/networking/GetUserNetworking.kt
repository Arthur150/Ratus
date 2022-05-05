package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.android.gms.tasks.Task

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import com.google.firebase.ktx.Firebase
import fr.univ.lyon1.lpiem.ratus.model.User

class GetUserNetworking {

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