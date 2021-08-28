package com.example.plantkeeper.application

import android.app.Application
import com.example.plantkeeper.di.applicationModule
import com.example.plantkeeper.di.presentationModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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