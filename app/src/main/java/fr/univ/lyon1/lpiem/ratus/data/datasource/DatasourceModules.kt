package fr.univ.lyon1.lpiem.ratus.data.datasource

import fr.univ.lyon1.lpiem.ratus.data.networking.GetUserNetworking
import org.koin.dsl.module

object DatasourceModules {
    private val firebaseModule = module {
        single<UserRemoteDataSource> {
            UserRemoteDataSourceImpl(get())
        }
    }

    val all = arrayOf(firebaseModule)
}