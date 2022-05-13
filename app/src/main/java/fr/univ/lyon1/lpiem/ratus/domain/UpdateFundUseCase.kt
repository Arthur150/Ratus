package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.FundRepository
import fr.univ.lyon1.lpiem.ratus.model.Fund

class UpdateFundUseCase(
    private val repository: FundRepository
) {
    suspend operator fun invoke(fund: Fund): Fund {
        return repository.updateFund(fund)
    }
}