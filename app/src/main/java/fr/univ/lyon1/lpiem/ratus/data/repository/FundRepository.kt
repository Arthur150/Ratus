package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.model.Fund

interface FundRepository {
    suspend fun getFundsWithContributorUID(uid: String): List<Fund>
    suspend fun getFundWithID(id: String): Fund
    suspend fun addContributor(id: String, uid: String): Fund
    suspend fun createFund(newFund: Fund): Fund
    suspend fun updateFund(updatedFund: Fund): Fund
}