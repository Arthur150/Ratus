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

        factory {
            GetRandomTricksUseCase(get())
        }

        factory {
            GetTrickUseCase(get())
        }
    }

    val all = arrayOf(firebaseModule)
}