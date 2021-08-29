package com.example.plantkeeper.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
}