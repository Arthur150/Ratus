package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class TrickNetworking {

    companion object {
        private const val TAG = "TrickNetworking"
        private const val COLLECTION_NAME = "tricks"
    }

    private val db = Firebase.firestore

    suspend fun getTricks(): QuerySnapshot? {

        return db.collection(COLLECTION_NAME)
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getTricks: ", exception)
            }
            .await()

    }

    suspend fun getTrick(id: String): DocumentSnapshot {
        return db.collection(COLLECTION_NAME)
            .document(id)
            .get()
            .addOnFailureListener {
                Log.e(TAG, "getTrick: ", it)
            }
            .await()
    }


}