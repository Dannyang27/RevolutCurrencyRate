package com.revolut.dannyang27.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.revolut.dannyang27.model.CurrencyRate
import com.revolut.dannyang27.repository.typeconverter.CurrencyTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Database(entities = [CurrencyRate::class], version = 1, exportSchema = false )
@TypeConverters(CurrencyTypeConverter::class)
abstract class MyRoomDatabase: RoomDatabase(), CoroutineScope{
    override val coroutineContext = Dispatchers.IO + Job()

    abstract fun currencyRateDao(): CurrencyRateDao

    companion object{
        var INSTANCE: MyRoomDatabase? = null

        fun getMyRoomDatabase(context: Context): MyRoomDatabase?{
            if(INSTANCE == null){
                synchronized(MyRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MyRoomDatabase::class.java, "myDatabase").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}