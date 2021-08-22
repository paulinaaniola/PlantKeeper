package com.example.plantkeeper.di

import com.example.plantkeeper.data.database.PlantsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {

    single { PlantsDatabase.create(androidContext()) }

}