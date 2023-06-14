package com.danp.laboratorio4

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.danp.laboratorio4.adapters.CountryAdapter
import com.danp.laboratorio4.entities.Countries
import com.danp.laboratorio4.entities.Country
import com.danp.laboratorio4.model.AppDatabase
import com.danp.laboratorio4.model.CountryDao
import com.danp.laboratorio4.paging.CountryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //readDataCountries(preDB())
        //val viewModel by viewModels<CountryViewModel>()
        val viewModel = CountryViewModel((preDB()))
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()
        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }

    fun preDB(): CountryDao {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-db"
        ).allowMainThreadQueries().build().countryDao()
        return db
    }
    fun readDataCountries(countryDao: CountryDao) {
        val inputStream: InputStream = applicationContext.assets.open("countries.txt")
        val json = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val countriesData: Countries = gson.fromJson(json, Countries::class.java)


        countriesData.countries.forEach {
            countryDao.insert(
                Country(
                    0,
                    it.name_en,
                    it.name_es,
                    it.continent_en,
                    it.continent_es,
                    it.capital_en,
                    it.capital_es,
                    it.dial_code,
                    it.code_2,
                    it.code_3,
                    it.tld,
                    it.km2
                )
            )
        }


    }


}
