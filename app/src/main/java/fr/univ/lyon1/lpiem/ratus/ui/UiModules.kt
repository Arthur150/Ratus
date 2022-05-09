package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.ui.budget.BudgetViewModel
import fr.univ.lyon1.lpiem.ratus.ui.homePage.HomePageViewModel
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
    }

    private val toolsModule = module {
        single {
            Tools()
        }
    }

    val all = arrayOf(viewModelModule, toolsModule)
}