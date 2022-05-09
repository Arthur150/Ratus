package fr.univ.lyon1.lpiem.ratus.data.datasource

import org.koin.dsl.module

object DatasourceModules {
    private val firebaseModule = module {
        single<UserRemoteDataSource> {
            UserRemoteDataSourceImpl(get(), get())
        }

        single<FundRemoteDataSource> {
            FundRemoteDataSourceImpl(get())
        }

        single<TrickRemoteDataSource> {
            TrickRemoteDataSourceImpl(get())
        }
    }

    val all = arrayOf(firebaseModule)
}