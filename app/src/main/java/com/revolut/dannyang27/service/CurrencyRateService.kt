package com.revolut.dannyang27.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.revolut.dannyang27.repository.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class CurrencyRateService : Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        toast("Service started")

        mHandler = Handler()
        mRunnable = Runnable {
            fetchCurrency()
        }

        mHandler.postDelayed(mRunnable, 1000)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Service destroyed.")
        mHandler.removeCallbacks(mRunnable)
    }

    private fun fetchCurrency(){
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getRates(this@CurrencyRateService)
            mHandler.postDelayed(mRunnable, 1000)
        }
    }
}