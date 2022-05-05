package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.data.networking.FundNetworking
import fr.univ.lyon1.lpiem.ratus.model.Fund

class FundRemoteDataSourceImpl(
    private val networking: FundNetworking
) : FundRemoteDataSource {
    override suspend fun getFundsWithContributorUID(uid: String): Result<List<Fund>> {
        return try {
            val query = networking.getFundsWithContributorUID(uid) ?: return Result.success(arrayListOf())
            val funds : ArrayList<Fund> = ArrayList()
            for (document in query.documents) {
                document.toObject(Fund::class.java)?.let {
                    funds.add(it)
                }
            }

            Result.success(funds)
        }
        catch (t : Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun getFundWithID(id: String): Result<Fund> {
        return try {
            val document = networking.getFundWithID(id)
            val fund = document?.toObject(Fund::class.java)

            if (fund != null){
                Result.success(fund)
            }
            else {
                Result.failure(IllegalStateException())
            }
        }
        catch (t : Throwable) {
            Result.failure(t)
        }
    }
}