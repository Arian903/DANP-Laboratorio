package com.danp.laboratorio3.Interface

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    onButtonClick: (String) -> Unit,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(12.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                onClick = {
                    onButtonClick("estudent")

                }) {
                Text(
                    color = Color.White,
                    text = "Crear Estudiante"
                )

            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta),
                onClick = {
                    onButtonClick("course")
                }) {
                Text(
                    color = Color.White,
                    text = "Crear Curso"
                )

            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta),
                onClick = {
                    onButtonClick("courseList")
                }) {
                Text(
                    color = Color.White,
                    text = "Lista"
                )

            }
        }
    }
}