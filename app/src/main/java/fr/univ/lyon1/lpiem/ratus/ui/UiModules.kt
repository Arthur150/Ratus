package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSourceImpl
import fr.univ.lyon1.lpiem.ratus.ui.budget.BudgetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModules {
    private val firebaseModule = module {
        viewModel {
            MainActivityViewModel(get())
        }
    }

    private val budgetModule = module {
        viewModel {
            BudgetViewModel(get())
        }
    }

    val all = arrayOf(firebaseModule, budgetModule)
}