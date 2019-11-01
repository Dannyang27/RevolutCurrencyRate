package com.revolut.dannyang27.repository.retrofit

import android.content.Context
import com.revolut.dannyang27.businesslogic.util.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.model.CurrencyRate
import com.revolut.dannyang27.repository.room.MyRoomDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://revolut.duckdns.org/"
    private var roomDatabase: MyRoomDatabase? = null

    private val service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(RateService::class.java)

    suspend fun getRates(context: Context, base: String = "EUR") {
        roomDatabase = MyRoomDatabase.getMyRoomDatabase(context)
        val response = service.getLatestRate(base)
        CurrencyManager.baseCurrency = base

        try {
            if (response.isSuccessful) {
                val currency = response.body()
                currency?.rates?.let {
                    val listOfCurrencies = mutableListOf(Currency(base, 1.00, true))
                    listOfCurrencies.addAll(currency.rates.map { Currency(it.key, it.value, false) })
                    val currencyRate = CurrencyRate(rateList = listOfCurrencies)
                    roomDatabase?.currencyRateDao()?.insert(currencyRate)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}