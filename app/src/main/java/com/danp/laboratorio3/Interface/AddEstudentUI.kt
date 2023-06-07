package com.danp.laboratorio3.Interface

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.danp.laboratorio3.Model.AppDatabase
import com.danp.laboratorio3.Model.Course
import com.danp.laboratorio3.Model.Estudent
import com.danp.laboratorio3.Model.EstudentCourseCrossRef


@Composable
fun AddEstudentUI(
    dataBase: AppDatabase,
    onButtonClick: (String) -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var estudentId by remember { mutableStateOf("") }
    val listCourse: List<Course> = dataBase.courseDao().getAll()
    var course: Course = Course(0,"")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = estudentId,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { estudentId = it },
                label = { Text(text = "CUI") }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { firstName = it },
                label = { Text(text = "Nombres") }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { lastName = it },
                label = { Text(text = "Apellidos") }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            dropList(listCourse = listCourse, selectedCourse = {
                listCourse.forEach { item ->
                    if (item.nameCourse == it ){
                        course = Course(item.courseId, item.nameCourse)
                        Log.i("Course: ", "${course?.courseId} - ${course?.nameCourse}")
                    }
                }
            })

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                onClick = {
                    val student = Estudent(estudentId.toInt(), firstName, lastName)

                    dataBase.estudentDao().insert(student)
                    dataBase.courseDao().setCourseWithEstudents(EstudentCourseCrossRef(student.estudentId, course.courseId))
                    Log.i("INFO: ", "${student.estudentId} - ${student.firstName} - ${student.lastName} / ${course?.courseId} - ${course?.nameCourse}")
                    onButtonClick("home")
                }) {
                Text(
                    color = Color.White,
                    text = "Registrar estudiante"
                )

            }
        }

    }

}

@Composable
fun dropList(
    listCourse: List<Course>,
    selectedCourse: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    var icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    OutlinedTextField(
        value = selectedItem,
        onValueChange = {},
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFiledSize = coordinates.size.toSize()
            },
        label = { Text(text = "Selected Item" )},
        trailingIcon = { Icon(icon, "", Modifier.clickable { expanded = !expanded } )}
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current){textFiledSize.width.toDp()})

    ) {
        listCourse.forEach { label ->
            DropdownMenuItem(onClick = {
                selectedItem = label.nameCourse
                selectedCourse(selectedItem)
                expanded = false
            }) {
                Text(text = label.nameCourse)
            }
        }
    }

}