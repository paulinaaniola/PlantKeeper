package com.example.plantkeeper.ui.addplant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import kotlinx.coroutines.launch

class AddPlantViewModel(private val plantsRepositoryImpl: PlantsRepository) : ViewModel() {

    var plantName: String = ""
    var wateringFrequency: Int? = null

    fun insertPlant() {
        wateringFrequency?.let { wateringFrequency ->
            viewModelScope.launch {
                plantsRepositoryImpl.insertPlant(
                    PlantDBO(
                        name = plantName,
                        wateringFrequency = wateringFrequency
                    )
                )
            }
        }
    }
}