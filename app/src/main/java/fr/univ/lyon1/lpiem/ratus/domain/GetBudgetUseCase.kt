package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.UserRepository
import fr.univ.lyon1.lpiem.ratus.model.Budget

class GetBudgetUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: String): Budget {
        return userRepository.getUserWithUID(uid).budget
    }
}