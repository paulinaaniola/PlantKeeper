package com.paulinaaniola.plantkeeper.ui.plantslist

import com.paulinaaniola.plantkeeper.R

enum class PlantWateringState(val resourceId: Int) {
    WATERING_REQUIRED(R.string.watering_required),
    NEAREST_WATERING(R.string.nearest_watering),
    FEELING_GOOD(R.string.feeling_good)
}