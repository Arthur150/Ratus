package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.ui.budget.BudgetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModules {
    private val viewModelModule = module {
        viewModel {
            MainActivityViewModel(get())
        }
        viewModel {
            BudgetViewModel(get())
        }
    }

    private val toolsModule = module {
        single {
            Tools()
        }
    }

    val all = arrayOf(viewModelModule, toolsModule)
}