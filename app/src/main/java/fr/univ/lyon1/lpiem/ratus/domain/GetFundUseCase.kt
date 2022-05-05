package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.FundRepository
import fr.univ.lyon1.lpiem.ratus.model.Fund

class GetFundUseCase (
    private val repository: FundRepository
) {
    suspend operator fun invoke(id : String) : Fund {
        return repository.getFundWithID(id)
    }
}