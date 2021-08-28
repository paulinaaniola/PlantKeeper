package com.example.plantkeeper.ui.plantslist.mapper

import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.*
import com.example.plantkeeper.utils.sorting.PlantSortingUtil
import org.koin.java.KoinJavaComponent.inject
import org.koin.android.ext.android.inject

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
            val wateringFrequency = "Watering: every ${plant.wateringFrequency.toDays()} days"
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