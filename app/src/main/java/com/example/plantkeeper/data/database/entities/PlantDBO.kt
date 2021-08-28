package com.example.plantkeeper.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate

@Entity(tableName = "plants_table")
data class PlantDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val wateringFrequency: Duration,
    val lastWateringDay: LocalDate
)