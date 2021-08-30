package com.example.plantkeeper.ui.plantslist

data class PlantViewState(
    val id: Int,
    val name: String,
    val wateringFrequency: WateringFrequency,
    val wateringState: PlantWateringState,
    val photoPath: String
)