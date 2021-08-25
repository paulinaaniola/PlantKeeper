package com.example.plantkeeper.data.repository

import com.example.plantkeeper.data.database.dao.PlantDao
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.database.mapper.DbMapper
import com.example.plantkeeper.domain.Plant
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {

    fun getAllPlants(): Flow<List<Plant>>

    fun insertPlant(plant: PlantDBO)
}