package com.paulinaaniola.plantkeeper.ui.plantslist.mapper

import com.paulinaaniola.plantkeeper.domain.Plant
import com.paulinaaniola.plantkeeper.ui.plantslist.PlantListItem

interface PlantsViewStateMapper {
    fun mapPlantsToListItems(plants: List<Plant>): List<PlantListItem>
}