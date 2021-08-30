package com.example.plantkeeper.ui.plantslist.mapper

import com.example.plantkeeper.R
import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.*
import com.example.plantkeeper.utils.sorting.PlantSortingUtil
import org.threeten.bp.Duration

class PlantsViewStateMapperImpl(
    private val plantSortingUtil: PlantSortingUtil
) : PlantsViewStateMapper {

    override fun mapPlantsToListItems(plants: List<Plant>): List<PlantListItem> {
        val plantsViewStates = mapPlantsToViewState(plants)
        val plantsListItems = mutableListOf<PlantListItem>()
        val requireWateringPlants =
            plantsViewStates.filter { it.wateringState == PlantWateringState.WATERING_REQUIRED }
        val nearestWateringPlants =
            plantsViewStates.filter { it.wateringState == PlantWateringState.NEAREST_WATERING }
        val feelingGoodPlants =
            plantsViewStates.filter { it.wateringState == PlantWateringState.FEELING_GOOD }
        if (requireWateringPlants.isNotEmpty()) {
            plantsListItems.add(Label(PlantWateringState.WATERING_REQUIRED))
            plantsListItems.addAll(requireWateringPlants.map { PlantItem(it) })
        }
        if (nearestWateringPlants.isNotEmpty()) {
            plantsListItems.add(Label(PlantWateringState.NEAREST_WATERING))
            plantsListItems.addAll(nearestWateringPlants.map { PlantItem(it) })
        }
        if (feelingGoodPlants.isNotEmpty()) {
            plantsListItems.add(Label(PlantWateringState.FEELING_GOOD))
            plantsListItems.addAll(feelingGoodPlants.map { PlantItem(it) })
        }
        return plantsListItems
    }

    private fun mapPlantsToViewState(plants: List<Plant>): List<PlantViewState> {
        val plantsViewStates = mutableListOf<PlantViewState>()
        plants.forEach { plant ->
            val wateringFrequency = getWateringFrequencyLabel(plant.wateringFrequency)
            val wateringState = plantSortingUtil.getPlantWateringState(
                plant.wateringFrequency,
                plant.lastWateringDay
            )
            plant.id?.let {
                plantsViewStates.add(
                    PlantViewState(
                        plant.id,
                        plant.name,
                        wateringFrequency,
                        wateringState,
                        plant.picturePath
                    )
                )
            }
        }
        return plantsViewStates
    }

    private fun getWateringFrequencyLabel(wateringFrequency: Duration): WateringFrequency {
        val numberOfDays = wateringFrequency.toDays()
        val shouldUseWeeks = Math.floorMod(numberOfDays, 7) == 0
        val numberOfWeeks = numberOfDays / 7
        return if (shouldUseWeeks) {
            WateringFrequency(numberOfWeeks.toInt(), R.string.watering_every_weeks)
        } else {
            WateringFrequency(numberOfDays.toInt(), R.string.watering_every_days)
        }
    }
}