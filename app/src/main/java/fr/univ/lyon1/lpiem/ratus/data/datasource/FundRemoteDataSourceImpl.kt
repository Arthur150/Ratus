package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.data.networking.FundNetworking
import fr.univ.lyon1.lpiem.ratus.data.networking.UserNetworking
import fr.univ.lyon1.lpiem.ratus.model.FirebaseFund
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.User

class FundRemoteDataSourceImpl(
    private val networking: FundNetworking,
    private val userNetworking: UserNetworking
) : FundRemoteDataSource {
    override suspend fun getFundsWithContributorUID(uid: String): Result<List<Fund>> {
        return try {
            val query = networking.getFundsWithContributorUID(uid) ?: return Result.success(arrayListOf())
            val funds: ArrayList<Fund> = ArrayList()
            for (document in query.documents) {
                document.toObject(FirebaseFund::class.java)?.let {
                    val contributors : ArrayList<User> = arrayListOf()
                    for (ref in it.contributorsRef) {
                        val userDocument = userNetworking.getUserWithReference(ref)
                        userDocument.toObject(User::class.java)?.let { contributor ->
                            contributors.add(contributor)
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
            val firebaseFund  = document?.toObject(FirebaseFund::class.java)

            val contributors : ArrayList<User> = arrayListOf()
            for (ref in firebaseFund?.contributorsRef ?: arrayListOf()) {
                val userDocument = userNetworking.getUserWithReference(ref)
                userDocument.toObject(User::class.java)?.let { contributor ->
                    contributors.add(contributor)
                }
            }
            val fund = firebaseFund?.toFund(contributors)

            if (fund != null){
                Result.success(fund)
            }
            else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}