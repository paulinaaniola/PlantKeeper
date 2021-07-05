package com.example.plantkeeper.plantslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantkeeper.model.database.PlantDO

class PlantsViewModel : ViewModel() {

    val plantsList = MutableLiveData<List<PlantDO>>()

}