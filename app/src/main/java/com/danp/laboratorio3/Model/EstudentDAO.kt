package com.danp.laboratorio3.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface EstudentDAO {
    @Insert
    fun insert(vararg estudent: Estudent)
}