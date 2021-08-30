package com.example.plantkeeper.data.database.mapper

import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.domain.Plant

class DbMapperImpl : DbMapper {

    override fun mapDbPlantsToDomain(plantsDb: List<PlantDBO>): List<Plant> {
        return plantsDb.map {
            Plant(
                it.id,
                it.name,
                it.wateringFrequency,
                it.lastWateringDay,
                it.photoPath
            )
        }
    }

    override fun mapDbPlantToDomain(plantDb: PlantDBO): Plant {
        return Plant(
            plantDb.id,
            plantDb.name,
            plantDb.wateringFrequency,
            plantDb.lastWateringDay,
            plantDb.photoPath
        )
    }

    override fun mapDomainPlantToDb(plant: Plant): PlantDBO {
        return PlantDBO(
            id = plant.id,
            name = plant.name,
            wateringFrequency = plant.wateringFrequency,
            lastWateringDay = plant.lastWateringDay,
            photoPath = plant.photoPath
        )
    }
}