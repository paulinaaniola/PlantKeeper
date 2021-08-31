package com.paulinaaniola.plantkeeper.data.repository

import com.paulinaaniola.plantkeeper.data.database.entities.PlantUpdateDBO
import com.paulinaaniola.plantkeeper.domain.Plant
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {

    fun getAllPlants(): Flow<List<Plant>>

    suspend fun insertPlant(plant: Plant)

    suspend fun updatePlantAsAlreadyWatered(plantUpdate: PlantUpdateDBO)

    suspend fun getPlantToEdit(plantId: Int): Plant

    suspend fun updatePlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)
}