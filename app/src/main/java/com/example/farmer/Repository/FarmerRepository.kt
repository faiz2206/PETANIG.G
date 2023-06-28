package com.example.farmer.Repository

import com.example.farmer.Dao.FarmerDao
import com.example.farmer.Model.Farmer
import kotlinx.coroutines.flow.Flow

class FarmerRepository(private val farmerDao: FarmerDao) {

        val allfarmers: Flow<List<Farmer>> = farmerDao.getAllFarmer()
        suspend fun insertFarmer(farmer: Farmer){
            farmerDao.insertFarmer(farmer)
        }
        suspend fun deleteFarmer(farmer: Farmer){
            farmerDao.deleteFarmer(farmer)
        }

        suspend fun updateFarmer(farmer: Farmer){
            farmerDao.updateFarmer(farmer)
        }
    }
