package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.TrickRepository
import fr.univ.lyon1.lpiem.ratus.model.Trick

class GetTricksUseCase(
    private val repository: TrickRepository
) {
    suspend operator fun invoke(): List<Trick> {
        return repository.getTricks()
    }
}