package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.data.networking.TrickNetworking
import fr.univ.lyon1.lpiem.ratus.model.Trick

class TrickRemoteDataSourceImpl(
    private val networking: TrickNetworking
) : TrickRemoteDataSource {
    override suspend fun getTricks(): Result<List<Trick>> {
        return try {
            val query = networking.getTricks() ?: return Result.success(arrayListOf())
            val tricks: ArrayList<Trick> = ArrayList()
            for (document in query.documents) {
                document.toObject(Trick::class.java)?.let {
                    tricks.add(it)
                }
            }

            Result.success(tricks)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun getTrick(id: String): Result<Trick> {
        return try {
            val document = networking.getTrick(id)
            val trick = document.toObject(Trick::class.java)

            if (trick != null) {
                Result.success(trick)
            } else {
                Result.failure(IllegalStateException())
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}