package com.example.plantkeeper.di

import com.example.plantkeeper.data.database.PlantsDatabase
import com.example.plantkeeper.data.database.mapper.DbMapper
import com.example.plantkeeper.data.database.mapper.DbMapperImpl
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.data.repository.PlantsRepositoryImpl
import com.example.plantkeeper.ui.validation.AddPlantValidator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val applicationModule = module {

    single { PlantsDatabase.create(androidContext()) }

    single<PlantsRepository> { PlantsRepositoryImpl(get(), get()) }

    single<DbMapper> { DbMapperImpl() }

    single { get<PlantsDatabase>().plantsDAO() }

    single<AddPlantValidator> { AddPlantValidator() }

}