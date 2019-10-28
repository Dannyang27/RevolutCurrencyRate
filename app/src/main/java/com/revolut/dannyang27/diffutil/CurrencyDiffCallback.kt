package com.revolut.dannyang27.diffutil

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffCallback(private val oldCurrencies: MutableList<Pair<String, Double>>,
                           private val newCurrencies: MutableList<Pair<String, Double>>): DiffUtil.Callback(){
    override fun getOldListSize() = oldCurrencies.size
    override fun getNewListSize() = newCurrencies.size
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldCurrencies[oldPos].first == newCurrencies[newPos].first
    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldCurrencies[oldPos] == newCurrencies[newPos]
}