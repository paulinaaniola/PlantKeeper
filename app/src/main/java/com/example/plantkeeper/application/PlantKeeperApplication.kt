package com.example.plantkeeper.application

import android.app.Application
import com.example.plantkeeper.di.applicationModule
import com.example.plantkeeper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PlantKeeperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PlantKeeperApplication)
            modules(listOf(applicationModule, presentationModule))
        }
    }
}