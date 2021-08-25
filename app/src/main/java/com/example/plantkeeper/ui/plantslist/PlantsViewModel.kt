package com.example.plantkeeper.ui.plantslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.domain.Plant
import kotlinx.coroutines.launch

class PlantsViewModel(private val plantsRepositoryImpl: PlantsRepository) : ViewModel() {

    val plantsList: LiveData<List<Plant>> = plantsRepositoryImpl.getAllPlants().asLiveData()

    fun addPlant() {
        viewModelScope.launch {
            plantsRepositoryImpl.insertPlant(PlantDBO(1, "Storczyk", 2))
            plantsRepositoryImpl.insertPlant(PlantDBO(2, "Monstera", 10))
        }
    }
}