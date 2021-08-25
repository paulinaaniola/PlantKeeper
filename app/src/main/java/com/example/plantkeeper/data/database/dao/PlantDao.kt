package com.example.plantkeeper.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.plantkeeper.data.database.entities.PlantDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Insert
    fun insertPlant(plant: PlantDBO)

    @Query("SELECT * FROM plants_table")
    fun getAllPlants(): Flow<List<PlantDBO>>

}