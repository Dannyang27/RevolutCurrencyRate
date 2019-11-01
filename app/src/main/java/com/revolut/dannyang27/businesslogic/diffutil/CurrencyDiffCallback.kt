package com.revolut.dannyang27.businesslogic.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.revolut.dannyang27.model.Currency

class CurrencyDiffCallback(private val oldCurrencies: List<Currency>,
                           private val newCurrencies: List<Currency>): DiffUtil.Callback(){

    override fun getOldListSize() = oldCurrencies.size
    override fun getNewListSize() = newCurrencies.size
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldCurrencies[oldPos].code == newCurrencies[newPos].code
    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldCurrencies[oldPos] == newCurrencies[newPos]
}