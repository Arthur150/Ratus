package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.FundRepository
import fr.univ.lyon1.lpiem.ratus.model.Fund

class GetUserFundsUseCase(
    private val repository: FundRepository
) {
    suspend operator fun invoke(uid : String) : List<Fund> {
        return repository.getFundsWithContributorUID(uid)
    }
}