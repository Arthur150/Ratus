package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.data.datasource.FundRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.model.Fund

class FundRepositoryImpl(
    private val dataSource: FundRemoteDataSource
) : FundRepository {
    override suspend fun getFundsWithContributorUID(uid: String): List<Fund> {
        return dataSource.getFundsWithContributorUID(uid).getOrThrow()
    }

    override suspend fun getFundWithID(id: String): Fund {
        return dataSource.getFundWithID(id).getOrThrow()
    }
}