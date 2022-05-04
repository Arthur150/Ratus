package fr.univ.lyon1.lpiem.ratus.data.networking

import org.koin.dsl.module

object NetworkingModules {

    private val firebaseModule = module {
        single {
            GetUserNetworking()
        }
    }

    val all = arrayOf(firebaseModule)

}