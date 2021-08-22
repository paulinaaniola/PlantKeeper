package com.example.plantkeeper.plantslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantkeeper.data.database.entities.PlantDBO

class PlantsViewModel : ViewModel() {

    val plantsList = MutableLiveData<List<PlantDBO>>()

}