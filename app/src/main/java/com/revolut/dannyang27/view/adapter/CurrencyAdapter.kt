package com.revolut.dannyang27.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.R
import com.revolut.dannyang27.businesslogic.diffutil.CurrencyDiffCallback
import com.revolut.dannyang27.businesslogic.util.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.view.viewholder.CurrencyViewHolder
import java.util.*

class CurrencyAdapter(private val currencies: MutableList<Currency>): RecyclerView.Adapter<CurrencyViewHolder>(){

    lateinit var sharedPref: SharedPreferences

    override fun getItemCount() = currencies.size

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.bind(currency)

        holder.itemView.setOnClickListener {
            swap(position)
            updateBaseCurrency(it.context, currency.code)
        }
    }

    fun updateList( newCurrencies: List<Currency>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(currencies, newCurrencies))
        currencies.clear()
        currencies.addAll(newCurrencies)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun swap(position: Int){
        Collections.swap(currencies, 0, position)
        notifyDataSetChanged()
    }

    private fun updateBaseCurrency(context: Context, currencyCode: String){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPref.edit()
            .putString("base", currencyCode)
            .apply()

        CurrencyManager.baseCurrency = currencyCode
    }
}