package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class TrickNetworking {

    companion object {
        private const val TAG = "TrickNetworking"
    }

    private val db = Firebase.firestore

    suspend fun getTricks() : QuerySnapshot? {

        return db.collection("tricks")
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getTricks: ", exception)
            }
            .await()

    }


}