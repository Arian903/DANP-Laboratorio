package com.danp.laboratorio4.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danp.laboratorio4.entities.Country


@Database(entities = [Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

}