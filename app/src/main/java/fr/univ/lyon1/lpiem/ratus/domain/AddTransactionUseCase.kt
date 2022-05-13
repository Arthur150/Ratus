package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.UserRepository
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User

class AddTransactionUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String, transaction: Transaction): User {
        val origin = userRepository.getUserWithUID(uid)
        return userRepository.addTransaction(
            uid,
            transaction,
            (origin.balance + transaction.amount)
        )
    }
}