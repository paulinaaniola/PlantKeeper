package com.example.plantkeeper.data.repository

import com.example.plantkeeper.data.database.dao.PlantDao
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.database.mapper.DbMapper
import kotlinx.coroutines.flow.map

class PlantsRepositoryImpl(
    private val plantsMapper: DbMapper,
    private val plantsDao: PlantDao
) : PlantsRepository {

    override fun getAllPlants() =
        plantsDao
            .getAllPlants()
            .map { plantsMapper.mapDbPlantsToDomain(it) }

    override fun insertPlant(plant: PlantDBO) {
        plantsDao.insertPlant(plant)
    }
}