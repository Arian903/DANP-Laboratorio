package com.danp.laboratorio3.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CourseDAO {
    @Insert
    fun insert(vararg course: Course)

    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Transaction
    @Query("SELECT * FROM course")
    fun getCourseWithEstudents(): List<CourseWithEstudents>

    @Insert
    fun setCourseWithEstudents(courseStudent: EstudentCourseCrossRef)
}