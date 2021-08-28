package com.example.plantkeeper.ui.plantslist.mapper

import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.PlantViewState

interface PlantsViewStateMapper {
    fun mapPlantsToViewState(plants: List<Plant>): List<PlantViewState>
}