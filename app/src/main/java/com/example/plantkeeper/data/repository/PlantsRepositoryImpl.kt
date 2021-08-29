package com.example.plantkeeper.data.repository

import com.example.plantkeeper.data.database.dao.PlantDao
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.database.entities.PlantUpdateDBO
import com.example.plantkeeper.data.database.mapper.DbMapper
import com.example.plantkeeper.domain.Plant
import kotlinx.coroutines.flow.map

class PlantsRepositoryImpl(
    private val plantsMapper: DbMapper,
    private val plantsDao: PlantDao
) : PlantsRepository {

    override fun getAllPlants() =
        plantsDao
            .getAllPlants()
            .map { plantsMapper.mapDbPlantsToDomain(it) }

    override suspend fun insertPlant(plant: Plant) {
        plantsDao.insertPlant(
            plantsMapper.mapDomainPlantToDb(plant)
        )
    }

    override suspend fun updatePlantAsAlreadyWatered(plantUpdate: PlantUpdateDBO) {
        plantsDao.update(plantUpdate)
    }
}