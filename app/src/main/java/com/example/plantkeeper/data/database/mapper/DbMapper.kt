package com.example.plantkeeper.data.database.mapper

import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.domain.Plant

interface DbMapper {

    fun mapDbPlantsToDomain(plant: List<PlantDBO>): List<Plant>

    fun mapDomainPlantToDb(plant: Plant): PlantDBO
}