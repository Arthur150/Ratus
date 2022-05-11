package fr.univ.lyon1.lpiem.ratus.domain

import fr.univ.lyon1.lpiem.ratus.data.repository.TrickRepository
import fr.univ.lyon1.lpiem.ratus.model.Trick

class GetRandomTricksUseCase(
    private val repository: TrickRepository
) {
    suspend operator fun invoke(): List<Trick> {
        val tricks = repository.getTricks().shuffled()
        return if (tricks.size > 4){
            tricks.subList(0,5).toList()
        }
        else {
            tricks.toList()
        }
    }
}