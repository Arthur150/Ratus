package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.ui.budget.BudgetViewModel
import fr.univ.lyon1.lpiem.ratus.ui.homePage.HomePageViewModel
import fr.univ.lyon1.lpiem.ratus.ui.trick_details.TrickDetailViewModel
import fr.univ.lyon1.lpiem.ratus.ui.trick_list.TrickListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModules {
    private val viewModelModule = module {
        viewModel {
            HomePageViewModel(get(), get(), get())
        }
        viewModel {
            BudgetViewModel(get())
        }
        viewModel {
            TrickListViewModel(get())
        }
        viewModel {
            TrickDetailViewModel(get())
        }
    }

    private val toolsModule = module {
        single {
            Tools()
        }
    }

    val all = arrayOf(viewModelModule, toolsModule)
}