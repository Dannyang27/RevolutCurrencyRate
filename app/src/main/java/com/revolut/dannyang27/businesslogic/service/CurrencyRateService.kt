package com.revolut.dannyang27.businesslogic.service

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.IBinder
import android.preference.PreferenceManager
import com.revolut.dannyang27.repository.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyRateService : Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private lateinit var pref: SharedPreferences

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler = Handler()
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        mRunnable = Runnable { fetchCurrency() }
        mHandler.postDelayed(mRunnable, 1000)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    private fun fetchCurrency(){
        CoroutineScope(Dispatchers.IO).launch {
            val base = pref.getString("base", "EUR")
            RetrofitClient.getRates(this@CurrencyRateService, base)
        }

        mHandler.postDelayed(mRunnable, 1000)
    }
}