package com.danp.laboratorio3.Model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class CourseWithEstudents(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "estudentId",
        associateBy = Junction(EstudentCourseCrossRef::class)
    )
    val estudents: List<Estudent>
)