package com.example.plantkeeper.di

import com.example.plantkeeper.ui.addeditplant.AddEditPlantViewModel
import com.example.plantkeeper.ui.plantslist.PlantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { PlantsViewModel(get(), get()) }
    viewModel { AddEditPlantViewModel(get()) }
}