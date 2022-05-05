package fr.univ.lyon1.lpiem.ratus.data.repository

import org.koin.dsl.module

object RepositoryModules {
    private val firebaseModule = module {
        single<UserRepository> {
            UserRepositoryImpl(get())
        }

        single<FundRepository> {
            FundRepositoryImpl(get())
        }
    }

    val all = arrayOf(firebaseModule)
}