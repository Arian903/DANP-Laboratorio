package com.danp.laboratorio3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.danp.laboratorio3.Interface.AddCourseUI
import com.danp.laboratorio3.Interface.AddEstudentUI
import com.danp.laboratorio3.Interface.Home
import com.danp.laboratorio3.Interface.ListCoursesWithStudentUI
import com.danp.laboratorio3.Model.AppDatabase
import com.danp.laboratorio3.Model.EstudentCourseCrossRef
import com.danp.laboratorio3.ui.theme.Laboratorio3Theme
import java.util.logging.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Laboratorio3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java, "database-registration"
                    ).allowMainThreadQueries().build()

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            Home(){
                                if(it == "estudent"){
                                        navController.navigate("estudent")
                                    }
                                    else if (it == "course"){
                                        navController.navigate("course")
                                    }else if (it == "courseList"){
                                    navController.navigate("courseList")
                                }
                            }
                        }
                        composable("estudent"){
                            AddEstudentUI(db,
                                onButtonClick = {
                                    navController.navigate(it)
                            } )
                        }
                        composable("course"){
                            AddCourseUI(
                                onButtonClick = {
                                    navController.navigate(it)
                                }
                            ,db)
                        }
                        composable("courseList"){

                            ListCoursesWithStudentUI(
                                db
                            )
                        }
                    }
                }
            }
        }
    }
}
