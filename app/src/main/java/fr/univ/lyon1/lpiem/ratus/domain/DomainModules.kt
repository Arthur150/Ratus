package fr.univ.lyon1.lpiem.ratus.domain

import org.koin.dsl.module

object DomainModules {
    private val firebaseModule = module {
        factory {
            GetUserUseCase(get())
        }
    }

    val all = arrayOf(firebaseModule)
}