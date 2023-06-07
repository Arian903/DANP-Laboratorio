package com.danp.laboratorio3.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course (
    @PrimaryKey val courseId: Int,
    @ColumnInfo(name = "name_course") val nameCourse: String
    )