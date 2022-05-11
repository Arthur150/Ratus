package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.TrickRepository
import fr.univ.lyon1.lpiem.ratus.model.Trick

class GetTrickUseCase(
    private val trickRepository: TrickRepository
) {
    suspend operator fun invoke(id : String) : Trick {
        return trickRepository.getTrick(id)
    }
}