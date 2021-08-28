package com.example.plantkeeper.ui.plantslist.mapper

import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.PlantViewState
import com.example.plantkeeper.ui.plantslist.PlantWateringState
import com.example.plantkeeper.utils.sorting.PlantSortingUtil
import org.koin.java.KoinJavaComponent.inject
import org.koin.android.ext.android.inject

class PlantsViewStateMapperImpl(
    private val plantSortingUtil: PlantSortingUtil
) : PlantsViewStateMapper {

    override fun mapPlantsToViewState(plants: List<Plant>): List<PlantViewState> {
        val plantsViewStates = mutableListOf<PlantViewState>()
        plants.forEach { plant ->
            val wateringFrequency = "Watering: every ${plant.wateringFrequency} days"
            val wateringState = plantSortingUtil.getPlantWateringState(
                plant.wateringFrequency,
                plant.lastWateringDay
            )
            plant.id?.let {
                plantsViewStates.add(
                    PlantViewState(plant.id, plant.name, wateringFrequency, wateringState)
                )
            }
        }
        return plantsViewStates
    }
}