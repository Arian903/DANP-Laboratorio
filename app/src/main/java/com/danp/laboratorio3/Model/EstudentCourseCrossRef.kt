package com.danp.laboratorio3.Model

import androidx.room.*

@Entity(primaryKeys = ["estudentId", "courseId"])
data class EstudentCourseCrossRef(
    val estudentId: Int,
    val courseId: Int
)

