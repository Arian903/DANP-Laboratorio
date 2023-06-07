package com.danp.laboratorio3.Interface

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import com.danp.laboratorio3.Model.AppDatabase
import com.danp.laboratorio3.Model.Course
import com.danp.laboratorio3.Model.Estudent

@Composable
fun AddCourseUI(
    onButtonClick: (String) -> Unit,
    dataBase: AppDatabase
){
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

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
                value = id,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { id = it },
                label = { Text(text = "Id curso") }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { name = it },
                label = { Text(text = "Nombre curso") }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                onClick = {
                    val cur = Course(id.toInt(), name)
                    Log.i("Curso:",  "(${cur.courseId}) - + ${cur.nameCourse}")
                    dataBase.courseDao().insert(cur)
                    onButtonClick("home")

                }) {
                Text(
                    color = Color.White,
                    text = "Registrar curso"
                )
            }
        }
    }
}