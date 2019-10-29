package com.revolut.dannyang27.viewmodel

import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.revolut.dannyang27.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.repository.room.MyRoomDatabase
import com.revolut.dannyang27.service.CurrencyRateService

class CurrencyViewModel : ViewModel(){
    private lateinit var serviceIntent: Intent
    private lateinit var sharedPref: SharedPreferences
    private var roomDatabase: MyRoomDatabase? = null
    private val currencyRate = MutableLiveData<List<Currency>>()

    fun getCurrencyRate(): LiveData<List<Currency>> = currencyRate

    fun initCurrencyObserver(activity: FragmentActivity){
        serviceIntent = Intent(activity, CurrencyRateService::class.java)
        activity.startService(serviceIntent)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        roomDatabase = MyRoomDatabase.getMyRoomDatabase(activity)
        roomDatabase?.currencyRateDao()?.getCurrencyRate()?.observe(activity, Observer {
            val base = sharedPref.getString("base", "EUR")
            val firstItem = it.rateList
                ?.filter { currency -> currency.code == base}
                ?.firstOrNull()

            val restOfList = it.rateList
                ?.filterNot { it.code == base}
                ?.toMutableList()

            firstItem?.rate?.let { rate ->
                CurrencyManager.rateCurrency = 1 / rate
                Log.d("REVOLUT", "Rate: $rate")
            }

            if(firstItem != null && restOfList != null){
                restOfList.add(0, firstItem)
                Log.d("REVOLUT", "Rest of list: ${restOfList.size}")
                Log.d("REVOLUT", "First of new item ${restOfList.first().code}")
            }

            currencyRate.postValue(restOfList)
        })
    }
}