package com.revolut.dannyang27.viewmodel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.revolut.dannyang27.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.model.CurrencyRate
import com.revolut.dannyang27.repository.room.MyRoomDatabase
import com.revolut.dannyang27.service.CurrencyRateService

class CurrencyViewModel : ViewModel(){
    private lateinit var serviceIntent: Intent
    private lateinit var sharedPref: SharedPreferences
    private var roomDatabase: MyRoomDatabase? = null
    private val selectedCurrency = MutableLiveData<Currency>()
    private val currencyRate = MutableLiveData<List<Currency>>()
    private var baseCurrency = ""

    fun getSelectedCurrency(): LiveData<Currency> = selectedCurrency
    fun getCurrencyRate(): LiveData<List<Currency>> = currencyRate

    fun initCurrencyObserver(activity: FragmentActivity){
        serviceIntent = Intent(activity, CurrencyRateService::class.java)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        roomDatabase = MyRoomDatabase.getMyRoomDatabase(activity)

        roomDatabase?.currencyRateDao()?.getCurrencyRate()?.observe(activity, Observer {
            baseCurrency = sharedPref.getString("base", "EUR")
            it?.let {currencyRate ->
                updateSelectedCurrency(currencyRate)
                updateNonSelectedCurrencies(currencyRate)
            }
        })
    }

    private fun updateSelectedCurrency(currencies: CurrencyRate){
        val currencyItem = currencies.rateList?.find { it.code == baseCurrency }

        currencyItem?.let {
            updateCurrentRate(1 / it.rate)
            selectedCurrency.postValue(currencyItem)
        }
    }

    private fun updateNonSelectedCurrencies(currencies: CurrencyRate){
        val restOfList = currencies.rateList
            ?.filterNot { it.code == baseCurrency}
            ?.toMutableList()

        currencyRate.postValue(restOfList)
    }

    fun updateCurrentValue(value: Double){
        CurrencyManager.currentValue = value
    }

    private fun updateCurrentRate(rate: Double){
        CurrencyManager.rateCurrency = rate
    }

    fun startService(context: Context){
        context.startService(serviceIntent)
    }

    fun stopService(context: Context){
        context.stopService(serviceIntent)
    }
}