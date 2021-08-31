package com.paulinaaniola.plantkeeper.application

import android.app.Application
import com.paulinaaniola.plantkeeper.di.applicationModule
import com.paulinaaniola.plantkeeper.di.presentationModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlantKeeperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PlantKeeperApplication)
            modules(listOf(applicationModule, presentationModule))
        }
    }
}