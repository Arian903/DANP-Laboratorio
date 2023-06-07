package com.danp.laboratorio3.Interface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danp.laboratorio3.Model.Course
import com.danp.laboratorio3.Model.CourseWithEstudents

@Composable
fun CardCourse(
    listCourses: CourseWithEstudents
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = listCourses.course.nameCourse)
            listCourses.estudents.forEach {
                val firstName = it.firstName
                val lastName = it.lastName
                Text(text = "$firstName - $lastName")
            }
        }
    }
}