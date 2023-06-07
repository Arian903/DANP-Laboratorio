package com.danp.laboratorio3.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Estudent::class, Course::class, EstudentCourseCrossRef::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDAO
    abstract fun estudentDao(): EstudentDAO
}