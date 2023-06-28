package com.example.farmer.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


    @Parcelize
    @Entity(tableName = "Farmer_table")
    data class Farmer (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val address:String,
    ) : Parcelable
