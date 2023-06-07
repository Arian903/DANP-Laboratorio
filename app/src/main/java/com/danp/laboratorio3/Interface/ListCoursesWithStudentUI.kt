package com.danp.laboratorio3.Interface

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import com.danp.laboratorio3.Model.AppDatabase
import com.danp.laboratorio3.Model.CourseWithEstudents


@Composable
fun ListCoursesWithStudentUI(
    dataBase: AppDatabase
){
    val listCoursesStudent: List<CourseWithEstudents> = dataBase.courseDao().getCourseWithEstudents()
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(listCoursesStudent) { courseStudent ->
                CardCourse(
                    listCourses = courseStudent
                )
            }
        }
    }
}