package fr.univ.lyon1.lpiem.ratus.data.datasource

import org.koin.dsl.module

object DatasourceModules {
    private val firebaseModule = module {
        single<UserRemoteDataSource> {
            UserRemoteDataSourceImpl(get())
        }

        single<FundRemoteDataSource> {
            FundRemoteDataSourceImpl(get())
        }
    }

    val all = arrayOf(firebaseModule)
}