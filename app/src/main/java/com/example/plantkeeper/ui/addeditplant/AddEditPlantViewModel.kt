package com.example.plantkeeper.ui.addeditplant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.domain.Plant
import kotlinx.coroutines.launch
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate

class AddEditPlantViewModel(private val plantsRepositoryImpl: PlantsRepository) : ViewModel() {

    var plantName: String = ""
    var wateringFrequency: Int? = null
    var photoPath: String = ""
    var wateringFrequencyUnit: WateringFrequencyUnit = WateringFrequencyUnit.DAYS

    var plantToEdit = MutableLiveData<Plant>()

    fun insertPlant(onPlantInsertedAction: () -> Unit) {
        wateringFrequency?.let { wateringFrequency ->
            viewModelScope.launch {
                plantsRepositoryImpl.insertPlant(
                    Plant(
                        null,
                        name = plantName,
                        wateringFrequency = getWateringDurationInDays(wateringFrequency.toLong()),
                        lastWateringDay = LocalDate.now(),
                        photoPath
                    )
                )
//                plantsRepositoryImpl.insertPlant(
//                    Plant(
//                        null,
//                        name = "Neares Storczyk",
//                        wateringFrequency = Duration.ofDays(4),
//                        lastWateringDay = LocalDate.now().minusDays(4),
//                        ""
//                    )
//                )
//                plantsRepositoryImpl.insertPlant(
//                    Plant(
//                        null,
//                        name = "Need water kwioatek",
//                        wateringFrequency = Duration.ofDays(5),
//                        lastWateringDay = LocalDate.now().minusDays(5),
//                        ""
//                    )
//                )
//                plantsRepositoryImpl.insertPlant(
//                    Plant(
//                        null,
//                        name = "Need water kwioatek",
//                        wateringFrequency = Duration.ofDays(5),
//                        lastWateringDay = LocalDate.now().minusDays(5),
//                        ""
//                    )
                //           )
            }.invokeOnCompletion {
                onPlantInsertedAction()
            }
        }
    }

    fun getPlantToEdit(plantId: Int) {
        viewModelScope.launch {
            val plant = plantsRepositoryImpl.getPlantToEdit(plantId)
            plantToEdit?.postValue(plant)
            plantName = plant.name
            wateringFrequency = plant.wateringFrequency.toDays().toInt()
            photoPath = plant.photoPath
        }
    }

    private fun getWateringDurationInDays(wateringFrequencyInput: Long): Duration {
        return if (wateringFrequencyUnit == WateringFrequencyUnit.WEEKS) {
            Duration.ofDays(wateringFrequencyInput * 7)
        } else {
            Duration.ofDays(wateringFrequencyInput)
        }
    }
}