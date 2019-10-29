package com.revolut.dannyang27.view.adapter

import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.CurrencyManager
import com.revolut.dannyang27.R
import com.revolut.dannyang27.diffutil.CurrencyDiffCallback
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.view.viewholder.CurrencyViewHolder
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast

class CurrencyAdapter(private val currencies: MutableList<Currency>): RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]

        val (currencyName, drawable) = CurrencyManager.getDrawableByName(currency.code)
        holder.currencyCode.text = currency.code
        holder.currencyName.text = currencyName
        holder.currencyRate.setText(String.format("%.2f", (currency.rate * CurrencyManager.rateCurrency!!)))

        Picasso.get()
            .load(drawable)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(it.context)
            pref.edit()
                .putString("base", currency.code)
                .apply()

            it.context.toast(currency.code)
        }
    }

    fun updateList( newCurrencies: MutableList<Currency>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(currencies, newCurrencies))
        currencies.clear()
        currencies.addAll(newCurrencies)
        diffResult.dispatchUpdatesTo(this)
    }
}