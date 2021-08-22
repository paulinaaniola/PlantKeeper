package com.example.plantkeeper.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants_table")
data class PlantDBO(
    @PrimaryKey val id: Int,
    val name: String,
    val wateringFrequency: Int
)