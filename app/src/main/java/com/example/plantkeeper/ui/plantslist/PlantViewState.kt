package com.example.plantkeeper.ui.plantslist

data class PlantViewState(
    val id: Int,
    val name: String,
    val wateringLabel: String,
    val wateringState: PlantWateringState,
    val picturePath: String
)