package com.paulinaaniola.plantkeeper.ui.plantslist

sealed class PlantListItem(val itemType: PlantListItemType)
data class Label(val wateringCategory: PlantWateringState) : PlantListItem(PlantListItemType.LABEL)
data class PlantItem(val plant: PlantViewState) : PlantListItem(PlantListItemType.PLANT)

enum class PlantListItemType {
    LABEL,
    PLANT
}