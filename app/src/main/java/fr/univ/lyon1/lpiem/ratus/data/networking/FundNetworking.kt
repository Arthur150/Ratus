package fr.univ.lyon1.lpiem.ratus.data.networking

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotFoundException
import fr.univ.lyon1.lpiem.ratus.model.Fund
import kotlinx.coroutines.tasks.await

class FundNetworking {

    companion object {
        private const val TAG = "FundNetworking"
    }

    private val db = Firebase.firestore

    suspend fun getFundsWithContributorUID(uid: String): QuerySnapshot? {
        val user = db.collection("users")
            .document(uid)
            .get()
            .await()

        return db.collection("funds")
            .whereArrayContains("contributors", user.reference)
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

    suspend fun addContributor(id: String, uid: String): DocumentSnapshot? {
        val contributor = db.collection("users")
            .document(uid)
            .get()
            .await()

        if (!contributor.exists()) {
            throw UserNotFoundException(uid)
        }

        db.collection("funds")
            .document(id)
            .update("contributors", FieldValue.arrayUnion(contributor.reference))
            .await()

        return getFundWithID(id)

    }

    suspend fun createFund(fund: Fund): DocumentSnapshot? {
        val contributorsList = ArrayList<DocumentReference>()
        for (contributor in fund.contributors) {
            val user = db.collection("users")
                .document(contributor.uid)
                .get()
                .await()
            contributorsList.add(user.reference)
        }

        val firebaseFund = fund.toFirebaseFund(contributorsList)

        val newFundRef = db.collection("funds")
            .add(firebaseFund.toHashMap())
            .await()

        return newFundRef.get()
            .await()
    }

    suspend fun updateFund(fund: Fund) : DocumentSnapshot? {
        db.collection("funds")
            .document(fund.id)
            .update("goal", fund.goal,
            "title", fund.title)
            .await()

        return getFundWithID(fund.id)
    }

}