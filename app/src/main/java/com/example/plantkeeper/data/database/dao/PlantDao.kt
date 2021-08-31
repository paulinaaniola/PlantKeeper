package com.example.plantkeeper.data.database.dao

import androidx.room.*
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.data.database.entities.PlantUpdateDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Insert
    suspend fun insertPlant(plant: PlantDBO)

    @Query("SELECT * FROM plants_table")
    fun getAllPlants(): Flow<List<PlantDBO>>

    @Update(entity = PlantDBO::class)
    suspend fun update(plantUpdate: PlantUpdateDBO)

    @Query("SELECT * FROM plants_table WHERE id is :plantId")
    suspend fun getPlantToEdit(plantId: Int): PlantDBO

    @Update
    suspend fun updatePlant(plant: PlantDBO)

    @Delete
    suspend fun deletePlant(plant: PlantDBO)
}