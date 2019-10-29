package com.revolut.dannyang27.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.revolut.dannyang27.repository.typeconverter.CurrencyTypeConverter

@Entity(tableName = "CurrencyRate")
data class CurrencyRate(@PrimaryKey
                        val id: Int = 1,
                        val base: String? = null,
                        val date: String? = null,
                        @TypeConverters(CurrencyTypeConverter::class)
                        val rateList: List<Currency>? = null)