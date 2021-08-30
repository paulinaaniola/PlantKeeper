package com.example.plantkeeper.data.database

import androidx.room.TypeConverter
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    fun toTimestamp(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toDuration(value: Long): Duration {
        return Duration.ofMillis(value)
    }

    @TypeConverter
    fun fromDuration(duration: Duration): Long {
        return duration.toMillis()
    }
}