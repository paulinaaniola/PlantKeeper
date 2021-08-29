package com.example.plantkeeper.data.database.entities

import androidx.room.Entity
import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

@Entity
data class PlantUpdateDBO(
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "lastWateringDay")
    var lastWateringDay: LocalDate? = null
)