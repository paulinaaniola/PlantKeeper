package com.example.plantkeeper.utils.sorting

import com.example.plantkeeper.ui.plantslist.PlantWateringState
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate

class PlantSortingUtil {

    fun getPlantWateringState(
        wateringFrequency: Duration,
        lastWateringDay: LocalDate
    ): PlantWateringState {
        val nextRequiredWatering = lastWateringDay.plusDays(wateringFrequency.toDays())
        return when {
            isWateringRequired(nextRequiredWatering) -> PlantWateringState.WATERING_REQUIRED
            isNearestWatering(nextRequiredWatering) -> PlantWateringState.NEAREST_WATERING
            else -> PlantWateringState.FEELING_GOOD
        }
    }

    private fun isWateringRequired(
        nextRequiredWatering: LocalDate
    ) =
        nextRequiredWatering.isBefore(LocalDate.now()) || nextRequiredWatering.isEqual(LocalDate.now())

    private fun isNearestWatering(
        nextRequiredWatering: LocalDate
    ) = LocalDate.now().plusDays(1).isEqual(nextRequiredWatering)
}