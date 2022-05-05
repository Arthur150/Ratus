package fr.univ.lyon1.lpiem.ratus.domain

import org.koin.dsl.module

object DomainModules {
    private val firebaseModule = module {
        factory {
            GetUserUseCase(get())
        }
    }

    private val budgetModule = module {
        factory {
            GetBudgetUseCase(get())
        }
    }

    val all = arrayOf(firebaseModule, budgetModule)
}