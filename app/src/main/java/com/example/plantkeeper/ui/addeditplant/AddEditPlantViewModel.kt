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
    var wateringFrequencyInput: Int? = null
    var photoPath: String = ""
    var wateringFrequencyUnit: WateringFrequencyUnit = WateringFrequencyUnit.DAYS

    var plantToEdit = MutableLiveData<Plant>()
    var viewState: ViewState? = null

    fun onSaveButtonAction() {
        wateringFrequencyInput?.let { wateringFrequency ->
            val wateringFrequencyDuration = getWateringDurationInDays(wateringFrequency.toLong())
            val plant = getPlantObjectFromInputs(wateringFrequencyDuration)
            if (viewState == ViewState.ADD) {
                insertPlant(plant)
            } else {
                editPlant(plant)
            }
        }
    }

    private fun getWateringDurationInDays(wateringFrequencyInput: Long): Duration {
        return if (wateringFrequencyUnit == WateringFrequencyUnit.WEEKS) {
            Duration.ofDays(wateringFrequencyInput * 7)
        } else {
            Duration.ofDays(wateringFrequencyInput)
        }
    }


    private fun insertPlant(plant: Plant) {
        viewModelScope.launch {
            plantsRepositoryImpl.insertPlant(plant)
        }
    }

    private fun editPlant(plant: Plant) {
        viewModelScope.launch {
            plantsRepositoryImpl.updatePlant(plant)
        }
    }

    private fun getPlantObjectFromInputs(wateringFrequency: Duration): Plant {
        val plantId = if (viewState == ViewState.ADD) null else plantToEdit.value?.id
        return Plant(
            plantId,
            name = plantName,
            wateringFrequency = wateringFrequency,
            lastWateringDay = LocalDate.now(),
            photoPath
        )
    }

    fun getPlantToEdit(plantId: Int) {
        viewModelScope.launch {
            val plant = plantsRepositoryImpl.getPlantToEdit(plantId)
            plantToEdit.postValue(plant)
            plantName = plant.name
            wateringFrequencyInput = plant.wateringFrequency.toDays().toInt()
            photoPath = plant.photoPath
        }
    }
}