package com.example.plantkeeper.ui.addplant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.domain.Plant
import kotlinx.coroutines.launch
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class AddPlantViewModel(private val plantsRepositoryImpl: PlantsRepository) : ViewModel() {

    var plantName: String = ""
    var wateringFrequency: Int? = null
    var photoPath: String = ""

    fun insertPlant(onPlantInsertedAction: () -> Unit) {
        wateringFrequency?.let { wateringFrequency ->
            viewModelScope.launch {
                plantsRepositoryImpl.insertPlant(
                    Plant(
                        null,
                        name = plantName,
                        wateringFrequency = Duration.ofDays(wateringFrequency.toLong()),
                        lastWateringDay = LocalDate.now(),
                        photoPath
                    )
                )
                plantsRepositoryImpl.insertPlant(
                    Plant(
                        null,
                        name = "Neares Storczyk",
                        wateringFrequency = Duration.ofDays(4),
                        lastWateringDay = LocalDate.now().minusDays(4),
                        ""
                    )
                )
                plantsRepositoryImpl.insertPlant(
                    Plant(
                        null,
                        name = "Need water kwioatek",
                        wateringFrequency = Duration.ofDays(5),
                        lastWateringDay = LocalDate.now().minusDays(5),
                        ""
                    )
                )
//                plantsRepositoryImpl.insertPlant(
//                    Plant(
//                        null,
//                        name = "Neares Storczyk",
//                        wateringFrequency = Duration.ofDays(2),
//                        lastWateringDay = LocalDate.now().minusDays(1)
//                    )
//                )
//                plantsRepositoryImpl.insertPlant(
//                    Plant(
//                        null,
//                        name = "Need water kwioatek",
//                        wateringFrequency = Duration.ofDays(4),
//                        lastWateringDay = LocalDate.now().minusDays(4)
//                    )
//                )
            }.invokeOnCompletion {
                onPlantInsertedAction()
            }
        }
    }
}