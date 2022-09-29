package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room.data.model.Cost
import com.example.room.data.model.CostType

@Dao
interface MoneyDAO {
    /* Таблица CostType*/
    @Query("SELECT * FROM $TYPES_TABLE")
    fun getAllTypes(): LiveData<List<CostType>>
/*    @Query("SELECT * FROM $TYPES_TABLE WHERE _id=:id")
    fun getType(id: Int)*/
    @Insert
    fun addType(costType: CostType)
    @Update
    fun saveType(costType: CostType)
    @Delete
    fun killType(costType: CostType)

    /*Таблица Cost*/
    @Query("SELECT * FROM $COSTS_TABLE")
    fun getAllCosts(): List<Cost>
    @Query("SELECT * FROM $COSTS_TABLE WHERE _id=:id")
    fun getCost(id:Int): Cost
    @Insert
    fun addCost(cost: Cost)
    @Update
    fun saveCost(cost: Cost)
    @Delete
    fun killCost(cost: Cost)

}