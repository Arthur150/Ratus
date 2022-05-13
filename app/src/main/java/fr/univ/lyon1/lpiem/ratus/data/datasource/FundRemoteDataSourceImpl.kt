package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.data.networking.FundNetworking
import fr.univ.lyon1.lpiem.ratus.data.networking.UserNetworking
import fr.univ.lyon1.lpiem.ratus.model.FirebaseFund
import fr.univ.lyon1.lpiem.ratus.model.FirebaseUser
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.User

class FundRemoteDataSourceImpl(
    private val networking: FundNetworking,
    private val userNetworking: UserNetworking
) : FundRemoteDataSource {
    override suspend fun getFundsWithContributorUID(uid: String): Result<List<Fund>> {
        return try {
            val query =
                networking.getFundsWithContributorUID(uid) ?: return Result.success(arrayListOf())
            val funds: ArrayList<Fund> = ArrayList()
            for (document in query.documents) {
                document.toObject(FirebaseFund::class.java)?.let {
                    val contributors: ArrayList<User> = arrayListOf()
                    for (ref in it.contributors) {
                        val userDocument = userNetworking.getUserWithReference(ref)
                        userDocument.toObject(FirebaseUser::class.java)?.let { contributor ->
                            contributors.add(contributor.toUser(arrayListOf()))
                        }
                    }
                    funds.add(it.toFund(contributors))
                }
            }

            Result.success(funds)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun getFundWithID(id: String): Result<Fund> {
        return try {
            val document = networking.getFundWithID(id)
            val firebaseFund = document?.toObject(FirebaseFund::class.java)

            val contributors: ArrayList<User> = arrayListOf()
            for (ref in firebaseFund?.contributors ?: arrayListOf()) {
                val userDocument = userNetworking.getUserWithReference(ref)
                userDocument.toObject(FirebaseUser::class.java)?.let { contributor ->
                    contributors.add(contributor.toUser(arrayListOf()))
                }
            }
            val fund = firebaseFund?.toFund(contributors)

            if (fund != null) {
                Result.success(fund)
            } else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun addContributor(id: String, uid: String): Result<Fund> {
        return try {
            val document = networking.addContributor(id, uid)
            val firebaseFund = document?.toObject(FirebaseFund::class.java)

            val contributors: ArrayList<User> = arrayListOf()
            for (ref in firebaseFund?.contributors ?: arrayListOf()) {
                val userDocument = userNetworking.getUserWithReference(ref)
                userDocument.toObject(FirebaseUser::class.java)?.let { contributor ->
                    contributors.add(contributor.toUser(arrayListOf()))
                }
            }
            val fund = firebaseFund?.toFund(contributors)

            if (fund != null) {
                Result.success(fund)
            } else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun createFund(newFund: Fund): Result<Fund> {
        return try {
            val document = networking.createFund(newFund)
            val firebaseFund = document?.toObject(FirebaseFund::class.java)

            val contributors: ArrayList<User> = arrayListOf()
            for (ref in firebaseFund?.contributors ?: arrayListOf()) {
                val userDocument = userNetworking.getUserWithReference(ref)
                userDocument.toObject(FirebaseUser::class.java)?.let { contributor ->
                    contributors.add(contributor.toUser(arrayListOf()))
                }
            }
            val fund = firebaseFund?.toFund(contributors)

            if (fund != null) {
                Result.success(fund)
            } else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun updateFund(updatedFund: Fund): Result<Fund> {
        return try {
            val document = networking.updateFund(updatedFund)
            val firebaseFund = document?.toObject(FirebaseFund::class.java)

            val contributors: ArrayList<User> = arrayListOf()
            for (ref in firebaseFund?.contributors ?: arrayListOf()) {
                val userDocument = userNetworking.getUserWithReference(ref)
                userDocument.toObject(FirebaseUser::class.java)?.let { contributor ->
                    contributors.add(contributor.toUser(arrayListOf()))
                }
            }
            val fund = firebaseFund?.toFund(contributors)

            if (fund != null) {
                Result.success(fund)
            } else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}