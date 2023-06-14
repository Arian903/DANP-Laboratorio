package com.danp.laboratorio4.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danp.laboratorio4.R
import com.danp.laboratorio4.entities.Country

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val idText = view.findViewById<TextView>(R.id.textViewId) as TextView
    private val nameText = view.findViewById<TextView>(R.id.textViewNameEs) as TextView
    private val continentText = view.findViewById<TextView>(R.id.textViewContinentEs) as TextView

    fun bind(country: Country) {
        with(country) {
            idText.text = country.id.toString()
            nameText.text = country.name_es
            continentText.text = country.capital_es
        }
    }
}