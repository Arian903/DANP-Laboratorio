package com.danp.laboratorio4.model

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Room
import com.danp.laboratorio4.entities.Country
import com.danp.laboratorio4.paging.CountryPagingSource
import kotlinx.coroutines.flow.Flow

class CountryRepository(
    private val countryDao: CountryDao
) {
    fun getCountries(): List<Country> {
        return countryDao.getAll()
    }
}