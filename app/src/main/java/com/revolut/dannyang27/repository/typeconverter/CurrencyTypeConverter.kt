package com.revolut.dannyang27.repository.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.revolut.dannyang27.model.Currency

class CurrencyTypeConverter {
    @TypeConverter
    fun toList(json: String): List<Currency>{
        val type = object :TypeToken<List<Currency>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun storeListToString(list: List<Currency>): String {
        val type = object :TypeToken<List<Currency>>() {}.type
        return Gson().toJson(list, type)
    }
}