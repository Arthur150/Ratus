package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.model.Trick

interface TrickRemoteDataSource {
    suspend fun getTricks(): Result<List<Trick>>
}