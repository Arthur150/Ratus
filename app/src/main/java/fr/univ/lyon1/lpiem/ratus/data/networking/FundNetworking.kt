package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FundNetworking {

    companion object {
        private const val TAG = "FundNetworking"
    }

    private val db = Firebase.firestore

    suspend fun getFundsWithContributorUID(uid: String): QuerySnapshot? {

        return db.collection("funds")
            .whereArrayContains("contributors", uid)
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getFundsWithContributorUID: ", exception)
            }
            .await()

    }

    suspend fun getFundWithID(id: String): DocumentSnapshot? {

        return db.collection("funds")
            .document(id)
            .get()
            .addOnFailureListener { exception ->
                Log.e(TAG, "getFundWithID: ", exception)
            }
            .await()

    }

}