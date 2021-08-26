package com.example.plantkeeper.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants_table")
data class PlantDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val wateringFrequency: Int
)