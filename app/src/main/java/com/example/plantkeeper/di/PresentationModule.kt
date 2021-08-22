package com.example.plantkeeper.di

import com.example.plantkeeper.plantslist.PlantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PlantsViewModel() }
}