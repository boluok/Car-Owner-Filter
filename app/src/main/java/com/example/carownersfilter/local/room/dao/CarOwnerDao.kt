package com.example.carownersfilter.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.carownersfilter.model.CarOwner
import kotlinx.coroutines.flow.Flow

@Dao
interface CarOwnerDao:BaseDao<CarOwner> {

    @Query("select * from car_owner ")
    fun getAllCarOwners(): Flow<List<CarOwner>>

    @Query("select * from car_owner ")
    fun getAllCarOwnersList():List<CarOwner>

    @Query("DELETE FROM car_owner")
    fun deleteAllCarOwners()

    @Transaction
    suspend fun updateCarOwners(car_owners: List<CarOwner>) {
        deleteAllCarOwners()
        insertAll(car_owners)
    }
}