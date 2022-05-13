package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.UserRepository
import fr.univ.lyon1.lpiem.ratus.model.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String, canCreateUser: Boolean = true): User {
        return userRepository.getUserWithUID(uid,canCreateUser)
    }
}