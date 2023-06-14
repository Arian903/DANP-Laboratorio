package com.danp.laboratorio4.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.danp.laboratorio4.entities.Country
import com.danp.laboratorio4.model.CountryDao
import com.danp.laboratorio4.model.CountryRepository
import kotlinx.coroutines.flow.Flow


class CountryViewModel(private val countryDao: CountryDao) : ViewModel() {
    private val countryRepository: CountryRepository = CountryRepository(countryDao)
    fun items(): Flow<PagingData<Country>> {
        val pager = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            CountryPagingSource(countryRepository)
        }.flow.cachedIn(viewModelScope)
        return pager
    }
}