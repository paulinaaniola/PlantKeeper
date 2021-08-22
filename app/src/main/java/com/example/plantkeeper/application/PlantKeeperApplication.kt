package com.example.plantkeeper.application

import android.app.Application
import com.example.plantkeeper.di.databaseModule
import com.example.plantkeeper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlantKeeperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@PlantKeeperApplication)
            modules(listOf(databaseModule, presentationModule))
        }
    }
}