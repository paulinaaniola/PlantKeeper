package com.paulinaaniola.plantkeeper.data.database.mapper

import com.paulinaaniola.plantkeeper.data.database.entities.PlantDBO
import com.paulinaaniola.plantkeeper.domain.Plant

interface DbMapper {

    fun mapDbPlantsToDomain(plant: List<PlantDBO>): List<Plant>

    fun mapDbPlantToDomain(plant: PlantDBO): Plant

    fun mapDomainPlantToDb(plant: Plant): PlantDBO
}