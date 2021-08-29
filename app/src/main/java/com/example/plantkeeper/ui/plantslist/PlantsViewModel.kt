package com.example.plantkeeper.ui.plantslist

import androidx.lifecycle.*
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.database.entities.PlantUpdateDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

class PlantsViewModel(
    private val plantsRepository: PlantsRepository,
    private val plantsViewStateMapper: PlantsViewStateMapper
) : ViewModel() {

    val plantsList: LiveData<List<PlantListItem>> =
        plantsRepository
            .getAllPlants()
            .map {
                plantsViewStateMapper
                    .mapPlantsToListItems(it)
            }.asLiveData()

    fun updatePlantAsAlreadyWatered(plantId: Int) {
        viewModelScope.launch {
            plantsRepository.updatePlantAsAlreadyWatered(PlantUpdateDBO(plantId, LocalDate.now()))
        }
    }
}