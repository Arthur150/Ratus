package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.ui.budget.BudgetViewModel
import fr.univ.lyon1.lpiem.ratus.ui.homePage.HomePageViewModel
import fr.univ.lyon1.lpiem.ratus.ui.transaction.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModules {
    private val viewModelModule = module {
        viewModel {
            HomePageViewModel(get())
        }
        viewModel {
            BudgetViewModel(get())
        }
        viewModel {
            TransactionViewModel(get())
        }
    }

    private val toolsModule = module {
        single {
            Tools()
        }
    }

    val all = arrayOf(viewModelModule, toolsModule)
}