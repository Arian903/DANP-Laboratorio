package com.danp.laboratorio4.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danp.laboratorio4.entities.Country

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlll(countries: List<Country>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Insert
    fun insert(vararg country: Country)

    @Query("SELECT * FROM countries")
    fun pagingSource(): PagingSource<Int, Country>

    @Query("DELETE FROM countries")
    suspend fun clearAll()

     @Query("SELECT * FROM countries")
     fun getAll(): List<Country>
}