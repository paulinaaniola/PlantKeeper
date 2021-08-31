package com.paulinaaniola.plantkeeper.di

import com.paulinaaniola.plantkeeper.ui.addeditplant.AddEditPlantViewModel
import com.paulinaaniola.plantkeeper.ui.plantslist.PlantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { PlantsViewModel(get(), get()) }
    viewModel { AddEditPlantViewModel(get()) }
}