package com.example.plantkeeper.plantslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.data.repository.PlantsRepositoryImpl
import com.example.plantkeeper.domain.Plant

class PlantsViewModel(private val plantsRepositoryImpl: PlantsRepository) : ViewModel() {

    val plantsList: LiveData<List<Plant>> = plantsRepositoryImpl.getAllPlants().asLiveData()

    fun addPlant() {
        plantsRepositoryImpl.insertPlant(PlantDBO(1, "Storczyk", 2))
        plantsRepositoryImpl.insertPlant(PlantDBO(2, "Monstera", 10))
    }
}