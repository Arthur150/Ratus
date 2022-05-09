package fr.univ.lyon1.lpiem.ratus.domain

import org.koin.dsl.module

object DomainModules {
    private val firebaseModule = module {
        factory {
            GetUserUseCase(get())
        }

        factory {
            GetUserFundsUseCase(get())
        }

        factory {
            GetFundUseCase(get())
        }

        factory {
            GetTricksUseCase(get())
        }

        factory {
            AddTransactionUseCase(get())
        }
    }

    private val budgetModule = module {

    }

    val all = arrayOf(firebaseModule, budgetModule)
}