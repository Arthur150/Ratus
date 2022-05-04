package fr.univ.lyon1.lpiem.ratus.ui

import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSourceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModules {
    private val firebaseModule = module {
        viewModel {
            parameters -> MainActivityViewModel(parameters.get(), get())
        }
    }

    val all = arrayOf(firebaseModule)
}