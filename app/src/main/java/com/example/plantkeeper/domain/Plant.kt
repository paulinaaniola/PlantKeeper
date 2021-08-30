package com.example.plantkeeper.domain

import org.threeten.bp.Duration
import org.threeten.bp.LocalDate

data class Plant(
    val id: Int?,
    val name: String,
    val wateringFrequency: Duration,
    val lastWateringDay: LocalDate,
    val photoPath: String
)