package fr.univ.lyon1.lpiem.ratus

import android.app.Application
import fr.univ.lyon1.lpiem.ratus.data.datasource.DatasourceModules
import fr.univ.lyon1.lpiem.ratus.data.networking.NetworkingModules
import fr.univ.lyon1.lpiem.ratus.data.repository.RepositoryModules
import fr.univ.lyon1.lpiem.ratus.domain.DomainModules
import fr.univ.lyon1.lpiem.ratus.ui.UiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RatusApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RatusApplication)
            modules(
                *UiModules.all,
                *DomainModules.all,
                *RepositoryModules.all,
                *DatasourceModules.all,
                *NetworkingModules.all
            )
        }
    }
}