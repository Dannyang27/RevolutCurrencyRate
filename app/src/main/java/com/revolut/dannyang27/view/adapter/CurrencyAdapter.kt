package com.revolut.dannyang27.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.R
import com.revolut.dannyang27.diffutil.CurrencyDiffCallback
import com.revolut.dannyang27.enum.FlagEnum
import com.revolut.dannyang27.view.viewholder.CurrencyViewHolder
import com.squareup.picasso.Picasso

class CurrencyAdapter(private val currencies: MutableList<Pair<String, Double>>): RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val pair = currencies[position]
        val (currencyName, drawable) = getDrawableByName(pair.first)
        holder.currencyCode.text = pair.first
        holder.currencyName.text = currencyName
        holder.currencyRate.setText(String.format("%.2f", pair.second))

        Picasso.get()
            .load(drawable)
            .into(holder.image)
    }

    fun updateList( newCurrencies: MutableList<Pair<String, Double>>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(currencies, newCurrencies))
        currencies.clear()
        currencies.addAll(newCurrencies)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun getDrawableByName(flagCode: String): Pair<String, Int>{
        return when(flagCode){
            FlagEnum.AUD.name -> Pair(FlagEnum.AUD.currencyName, FlagEnum.AUD.drawable)
            FlagEnum.BGN.name -> Pair(FlagEnum.BGN.currencyName, FlagEnum.BGN.drawable)
            FlagEnum.BRL.name -> Pair(FlagEnum.BRL.currencyName, FlagEnum.BRL.drawable)
            FlagEnum.CAD.name -> Pair(FlagEnum.CAD.currencyName, FlagEnum.CAD.drawable)
            FlagEnum.CHF.name -> Pair(FlagEnum.CHF.currencyName, FlagEnum.CHF.drawable)
            FlagEnum.CNY.name -> Pair(FlagEnum.CNY.currencyName, FlagEnum.CNY.drawable)
            FlagEnum.CZK.name -> Pair(FlagEnum.CZK.currencyName, FlagEnum.CZK.drawable)
            FlagEnum.DKK.name -> Pair(FlagEnum.DKK.currencyName, FlagEnum.DKK.drawable)
            FlagEnum.GBP.name -> Pair(FlagEnum.GBP.currencyName, FlagEnum.GBP.drawable)
            FlagEnum.HKD.name -> Pair(FlagEnum.HKD.currencyName, FlagEnum.HKD.drawable)
            FlagEnum.HRK.name -> Pair(FlagEnum.HRK.currencyName, FlagEnum.HRK.drawable)
            FlagEnum.HUF.name -> Pair(FlagEnum.HUF.currencyName, FlagEnum.HUF.drawable)
            FlagEnum.IDR.name -> Pair(FlagEnum.IDR.currencyName, FlagEnum.IDR.drawable)
            FlagEnum.ILS.name -> Pair(FlagEnum.ILS.currencyName, FlagEnum.ILS.drawable)
            FlagEnum.INR.name -> Pair(FlagEnum.INR.currencyName, FlagEnum.INR.drawable)
            FlagEnum.ISK.name -> Pair(FlagEnum.ISK.currencyName, FlagEnum.ISK.drawable)
            FlagEnum.JPY.name -> Pair(FlagEnum.JPY.currencyName, FlagEnum.JPY.drawable)
            FlagEnum.MXN.name -> Pair(FlagEnum.MXN.currencyName, FlagEnum.MXN.drawable)
            FlagEnum.MYR.name -> Pair(FlagEnum.MYR.currencyName, FlagEnum.MYR.drawable)
            FlagEnum.KRW.name -> Pair(FlagEnum.KRW.currencyName, FlagEnum.KRW.drawable)
            FlagEnum.NOK.name -> Pair(FlagEnum.NOK.currencyName, FlagEnum.NOK.drawable)
            FlagEnum.NZD.name -> Pair(FlagEnum.NZD.currencyName, FlagEnum.NZD.drawable)
            FlagEnum.PHP.name -> Pair(FlagEnum.PHP.currencyName, FlagEnum.PHP.drawable)
            FlagEnum.PLN.name -> Pair(FlagEnum.PLN.currencyName, FlagEnum.PLN.drawable)
            FlagEnum.RON.name -> Pair(FlagEnum.RON.currencyName, FlagEnum.RON.drawable)
            FlagEnum.RUB.name -> Pair(FlagEnum.RUB.currencyName, FlagEnum.RUB.drawable)
            FlagEnum.SEK.name -> Pair(FlagEnum.SEK.currencyName, FlagEnum.SEK.drawable)
            FlagEnum.SGD.name -> Pair(FlagEnum.SGD.currencyName, FlagEnum.SGD.drawable)
            FlagEnum.THB.name -> Pair(FlagEnum.THB.currencyName, FlagEnum.THB.drawable)
            FlagEnum.TRY.name -> Pair(FlagEnum.TRY.currencyName, FlagEnum.TRY.drawable)
            FlagEnum.USD.name -> Pair(FlagEnum.USD.currencyName, FlagEnum.USD.drawable)
            FlagEnum.ZAR.name -> Pair(FlagEnum.ZAR.currencyName, FlagEnum.ZAR.drawable)
            else -> Pair(FlagEnum.EUR.currencyName, FlagEnum.EUR.drawable)
        }
    }
}