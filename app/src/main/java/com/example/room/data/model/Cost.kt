package com.example.room.data.model

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.room.data.COSTS_TABLE
import com.example.room.data.converters.DatesTypeConverter
import java.util.*

@Entity(tableName = COSTS_TABLE)
data class Cost (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Int,
    @ColumnInfo(index = true)
    var typeId: Int = 0,
    var cost: Float,
    var description: String = "",

    var buyDate: Date = Date()
)

