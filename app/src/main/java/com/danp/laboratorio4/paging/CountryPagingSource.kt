package com.danp.laboratorio4.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danp.laboratorio4.entities.Country
import com.danp.laboratorio4.model.CountryRepository
import java.io.IOException

class CountryPagingSource(
    private val countryRepository: CountryRepository
) : PagingSource<Int, Country>(){

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val nextPageNumber = params.key ?: 0
            val countries = countryRepository.getCountries()


            return LoadResult.Page(
                data = countries ,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (countries.isNotEmpty()) nextPageNumber + 1 else null
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}