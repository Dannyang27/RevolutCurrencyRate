package com.revolut.dannyang27.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.revolut.dannyang27.model.CurrencyRate

@Dao
interface CurrencyRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyRate: CurrencyRate)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(currencyRate: CurrencyRate)

    @Delete
    fun delete(currencyRate: CurrencyRate)

    @Query("SELECT * FROM CurrencyRate")
    fun getCurrencyRate(): LiveData<CurrencyRate>
}