package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.model.Trick

interface TrickRepository {
    suspend fun getTricks() : List<Trick>
}