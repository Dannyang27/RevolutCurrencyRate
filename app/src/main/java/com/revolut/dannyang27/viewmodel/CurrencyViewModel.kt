package com.revolut.dannyang27.viewmodel

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.revolut.dannyang27.model.CurrencyRate
import com.revolut.dannyang27.repository.room.MyRoomDatabase
import com.revolut.dannyang27.service.CurrencyRateService

class CurrencyViewModel : ViewModel(){
    private lateinit var serviceIntent: Intent
    private var roomDatabase: MyRoomDatabase? = null
    private val currencyRate = MutableLiveData<MutableList<Pair<String, Double>>>()

    fun getCurrencyRate(): LiveData<MutableList<Pair<String, Double>>> = currencyRate

    fun initCurrencyObserver(activity: FragmentActivity){
        serviceIntent = Intent(activity, CurrencyRateService::class.java)
        activity.startService(serviceIntent)

        roomDatabase = MyRoomDatabase.getMyRoomDatabase(activity)
        roomDatabase?.currencyRateDao()?.getCurrencyRate()?.observe(activity, Observer {
            currencyRate.postValue(getPairOfCurrencies(it))
        })
    }

    private fun getPairOfCurrencies(currencies: CurrencyRate): MutableList<Pair<String, Double>>{
        val pairs: MutableList<Pair<String, Double>> = mutableListOf()
        pairs.add(Pair("EUR", 1.00))
        pairs.add(Pair("AUD", currencies.AUD!!))
        pairs.add(Pair("BGN", currencies.BGN!!))
        pairs.add(Pair("BRL", currencies.BRL!!))
        pairs.add(Pair("CAD", currencies.CAD!!))
        pairs.add(Pair("CHF", currencies.CHF!!))
        pairs.add(Pair("CNY", currencies.CNY!!))
        pairs.add(Pair("CZK", currencies.CZK!!))
        pairs.add(Pair("DKK", currencies.DKK!!))
        pairs.add(Pair("GBP", currencies.GBP!!))
        pairs.add(Pair("HKD", currencies.HKD!!))
        pairs.add(Pair("HRK", currencies.HRK!!))
        pairs.add(Pair("HUF", currencies.HUF!!))
        pairs.add(Pair("IDR", currencies.IDR!!))
        pairs.add(Pair("ILS", currencies.ILS!!))
        pairs.add(Pair("ISK", currencies.ISK!!))
        pairs.add(Pair("INR", currencies.INR!!))
        pairs.add(Pair("JPY", currencies.JPY!!))
        pairs.add(Pair("KRW", currencies.KRW!!))
        pairs.add(Pair("MXN", currencies.MXN!!))
        pairs.add(Pair("MYR", currencies.MYR!!))
        pairs.add(Pair("NOK", currencies.NOK!!))
        pairs.add(Pair("NZD", currencies.NZD!!))
        pairs.add(Pair("PHP", currencies.PHP!!))
        pairs.add(Pair("PLN", currencies.PLN!!))
        pairs.add(Pair("RON", currencies.RON!!))
        pairs.add(Pair("RUB", currencies.RUB!!))
        pairs.add(Pair("SEK", currencies.SEK!!))
        pairs.add(Pair("SGD", currencies.SGD!!))
        pairs.add(Pair("THB", currencies.THB!!))
        pairs.add(Pair("TRY", currencies.TRY!!))
        pairs.add(Pair("USD", currencies.USD!!))
        pairs.add(Pair("ZAR", currencies.ZAR!!))

        return pairs
    }
}