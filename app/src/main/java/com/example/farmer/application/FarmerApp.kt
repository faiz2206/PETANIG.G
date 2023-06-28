package com.example.farmer.application
import android.app.Application
import com.example.farmer.Repository.FarmerRepository


class FarmerApp: Application() {
    val database by lazy { FarmerDatabase.getDatabase(this) }
    val repository by lazy { FarmerRepository(database.farmerDao()) }
}