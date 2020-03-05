package com.example.carownersfilter.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carownersfilter.local.room.dao.CarOwnerDao
import com.example.carownersfilter.model.CarOwner

@Database(entities = [CarOwner::class], version = 1,exportSchema = false)

abstract class CarOwnerDatabase :RoomDatabase() {
    abstract fun carOwnerDao():CarOwnerDao
}

