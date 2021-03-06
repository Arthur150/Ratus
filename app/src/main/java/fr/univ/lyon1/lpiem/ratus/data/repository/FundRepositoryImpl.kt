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

    override suspend fun addContributor(id: String, uid: String): Fund {
        return dataSource.addContributor(id, uid).getOrThrow()
    }

    override suspend fun createFund(newFund: Fund): Fund {
        return dataSource.createFund(newFund).getOrThrow()
    }

    override suspend fun updateFund(updatedFund: Fund): Fund {
        return dataSource.updateFund(updatedFund).getOrThrow()
    }
}