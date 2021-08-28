package com.example.plantkeeper.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantkeeper.data.database.dao.PlantDao
import com.example.plantkeeper.data.database.entities.PlantDBO


@Database(entities = [(PlantDBO::class)], version = 1)
@TypeConverters(Converters::class)
abstract class PlantsDatabase : RoomDatabase() {

    abstract fun plantsDAO(): PlantDao

    companion object {
        fun create(context: Context): PlantsDatabase {

            return Room.databaseBuilder(
                context,
                PlantsDatabase::class.java,
                "plants_database"
            ).build()
        }
    }
}