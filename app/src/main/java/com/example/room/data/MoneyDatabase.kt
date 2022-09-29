package com.example.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.room.data.converters.DatesTypeConverter
import com.example.room.data.model.Cost
import com.example.room.data.model.CostType


@Database(entities = [CostType::class, Cost::class], version = 1)
@TypeConverters(DatesTypeConverter::class)
abstract class MoneyDatabase: RoomDatabase() {

    abstract fun moneyDAO(): MoneyDAO

}
