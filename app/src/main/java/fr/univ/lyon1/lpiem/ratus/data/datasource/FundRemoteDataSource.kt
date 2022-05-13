package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.model.Fund

interface FundRemoteDataSource {
    suspend fun getFundsWithContributorUID(uid: String): Result<List<Fund>>

    suspend fun getFundWithID(id: String): Result<Fund>

    suspend fun addContributor(id: String, uid: String): Result<Fund>

    suspend fun createFund(newFund: Fund): Result<Fund>

    suspend fun updateFund(updatedFund: Fund): Result<Fund>
}