package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.FundRepository
import fr.univ.lyon1.lpiem.ratus.model.Fund

class AddContributorUseCase(
    private val repository: FundRepository
) {
    suspend operator fun invoke(id: String, uid: String): Fund {
        return try {
            repository.addContributor(id, uid)
        } catch (t: Throwable) {
            throw t
        }
    }
}