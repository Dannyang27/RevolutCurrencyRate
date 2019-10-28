package com.revolut.dannyang27.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.R
import com.revolut.dannyang27.view.viewholder.CurrencyViewHolder
import com.squareup.picasso.Picasso

class CurrencyAdapter(private val currencies: MutableList<String>): RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.currencyCode.text = "USD"
        holder.currencyName.text = "US Dollar"
        holder.currencyRate.setText("1182.02")
        holder.currencyRate.isFocusable = false

        Picasso.get()
            .load(R.drawable.sgd)
            .into(holder.image)
    }
}