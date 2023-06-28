package com.example.farmer.Dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmer.Model.Farmer
import java.util.concurrent.Flow
import kotlin.collections.List as List

@Dao
interface FarmerDao {
        @Query("SELECT * FROM Farmer_table ORDER BY name ASC")
        fun  getAllFarmer():kotlinx.coroutines.flow.Flow<List<Farmer>>

        @Insert
        suspend fun  insertFarmer(farmer: Farmer)

        @Delete
        suspend fun deleteFarmer(farmer: Farmer)

        @Update fun updateFarmer(farmer: Farmer)
    }

