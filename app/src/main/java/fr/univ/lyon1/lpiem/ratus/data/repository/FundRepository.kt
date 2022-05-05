package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.User

interface FundRepository {
    suspend fun getFundsWithContributorUID(uid : String) : List<Fund>
    suspend fun getFundWithID(id : String) : Fund
}