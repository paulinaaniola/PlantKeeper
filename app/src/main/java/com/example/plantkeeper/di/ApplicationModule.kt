package com.example.plantkeeper.di

import com.example.plantkeeper.data.database.PlantsDatabase
import com.example.plantkeeper.data.database.mapper.DbMapper
import com.example.plantkeeper.data.database.mapper.DbMapperImpl
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.data.repository.PlantsRepositoryImpl
import com.example.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapper
import com.example.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapperImpl
import com.example.plantkeeper.utils.sorting.PlantSortingUtil
import com.example.plantkeeper.utils.validation.AddPlantValidator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val applicationModule = module {

    single { PlantsDatabase.create(androidContext()) }

    single<PlantsRepository> { PlantsRepositoryImpl(get(), get()) }

    single<DbMapper> { DbMapperImpl() }

    single { get<PlantsDatabase>().plantsDAO() }

    single { AddPlantValidator() }

    single { PlantSortingUtil() }

    single<PlantsViewStateMapper> { PlantsViewStateMapperImpl(get()) }
}