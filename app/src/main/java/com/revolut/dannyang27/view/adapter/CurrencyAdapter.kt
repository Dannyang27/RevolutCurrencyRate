package com.revolut.dannyang27.view.adapter

import android.preference.PreferenceManager
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.CurrencyManager
import com.revolut.dannyang27.R
import com.revolut.dannyang27.diffutil.CurrencyDiffCallback
import com.revolut.dannyang27.view.viewholder.CurrencyViewHolder
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast

class CurrencyAdapter(private val currencies: MutableList<Pair<String, Double>>): RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val pair = currencies[position]
        val (currencyName, drawable) = CurrencyManager.getDrawableByName(pair.first)
        holder.currencyCode.text = pair.first
        holder.currencyName.text = currencyName
        holder.currencyRate.setText(String.format("%.2f", pair.second))
        holder.currencyRate.isFocusable = false
        holder.currencyRate.isFocusableInTouchMode = false
        holder.currencyRate.inputType = InputType.TYPE_NULL

        Picasso.get()
            .load(drawable)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(it.context)
            pref.edit()
                .putString("base", pair.first)
                .apply()

            it.context.toast(pair.first)
        }
    }

    fun updateList( newCurrencies: MutableList<Pair<String, Double>>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(currencies, newCurrencies))
        currencies.clear()
        currencies.addAll(newCurrencies)
        diffResult.dispatchUpdatesTo(this)
    }
}