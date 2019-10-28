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
    private val currencyRate = MutableLiveData<CurrencyRate>()

    fun getCurrencyRate(): LiveData<CurrencyRate> = currencyRate

    fun initCurrencyObserver(activity: FragmentActivity){
        serviceIntent = Intent(activity, CurrencyRateService::class.java)
        activity.startService(serviceIntent)

        roomDatabase = MyRoomDatabase.getMyRoomDatabase(activity)
        roomDatabase?.currencyRateDao()?.getCurrencyRate()?.observe(activity, Observer {
            currencyRate.postValue(it)
        })
    }
}