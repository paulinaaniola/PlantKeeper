package com.paulinaaniola.plantkeeper.data.repository

import com.paulinaaniola.plantkeeper.data.database.dao.PlantDao
import com.paulinaaniola.plantkeeper.data.database.entities.PlantUpdateDBO
import com.paulinaaniola.plantkeeper.data.database.mapper.DbMapper
import com.paulinaaniola.plantkeeper.domain.Plant
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

    override suspend fun getPlantToEdit(plantId: Int): Plant {
        return plantsDao.getPlantToEdit(plantId).let { plantsMapper.mapDbPlantToDomain(it) }
    }

    override suspend fun updatePlant(plant: Plant) {
        plantsDao.updatePlant(plantsMapper.mapDomainPlantToDb(plant))
    }

    override suspend fun deletePlant(plant: Plant) {
        plantsDao.deletePlant(plantsMapper.mapDomainPlantToDb(plant))
    }
}