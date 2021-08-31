package com.paulinaaniola.plantkeeper.di

import com.paulinaaniola.plantkeeper.data.database.PlantsDatabase
import com.paulinaaniola.plantkeeper.data.database.mapper.DbMapper
import com.paulinaaniola.plantkeeper.data.database.mapper.DbMapperImpl
import com.paulinaaniola.plantkeeper.data.repository.PlantsRepository
import com.paulinaaniola.plantkeeper.data.repository.PlantsRepositoryImpl
import com.paulinaaniola.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapper
import com.paulinaaniola.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapperImpl
import com.paulinaaniola.plantkeeper.utils.sorting.PlantSortingUtil
import com.paulinaaniola.plantkeeper.utils.validation.AddPlantValidator
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