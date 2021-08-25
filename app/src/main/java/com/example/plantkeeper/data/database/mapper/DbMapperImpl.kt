package com.example.plantkeeper.data.database.mapper

import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.domain.Plant

class DbMapperImpl : DbMapper {

    override fun mapDbPlantsToDomain(plantsDb: List<PlantDBO>): List<Plant> {
        val plants = mutableListOf<Plant>()
        plantsDb.forEach { plant ->
            val watering = "Watering: every ${plant.wateringFrequency} days"
            plants.add(Plant(plant.name, watering))
        }
        return plants
    }
}