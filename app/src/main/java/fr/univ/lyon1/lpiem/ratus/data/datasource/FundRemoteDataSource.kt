package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.model.Fund

interface FundRemoteDataSource {
    suspend fun getFundsWithContributorUID(uid : String) : Result<List<Fund>>

    suspend fun getFundWithID(id : String) : Result<Fund>
}