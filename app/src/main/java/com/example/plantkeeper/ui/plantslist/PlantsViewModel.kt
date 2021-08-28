package com.example.plantkeeper.ui.plantslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.repository.PlantsRepository
import com.example.plantkeeper.domain.Plant
import com.example.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PlantsViewModel(
    private val plantsRepository: PlantsRepository,
    private val plantsViewStateMapper: PlantsViewStateMapper
) : ViewModel() {

    val plantsList: LiveData<List<PlantViewState>> =
        plantsRepository
            .getAllPlants()
            .map {
                plantsViewStateMapper.mapPlantsToViewState(it)
            }.asLiveData()

}