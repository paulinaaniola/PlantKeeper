package com.example.plantkeeper.ui.plantslist.mapper

import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.PlantListItem
import com.example.plantkeeper.ui.plantslist.PlantViewState

interface PlantsViewStateMapper {
    fun mapPlantsToListItems(plants: List<Plant>): List<PlantListItem>
}