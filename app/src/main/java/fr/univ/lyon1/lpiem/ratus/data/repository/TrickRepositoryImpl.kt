package fr.univ.lyon1.lpiem.ratus.data.repository

import fr.univ.lyon1.lpiem.ratus.data.datasource.TrickRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.model.Trick

class TrickRepositoryImpl(
    private val dataSource: TrickRemoteDataSource
) : TrickRepository {
    override suspend fun getTricks(): List<Trick> {
        return dataSource.getTricks().getOrThrow()
    }

    override suspend fun getTrick(id: String): Trick {
        return dataSource.getTrick(id).getOrThrow()
    }
}