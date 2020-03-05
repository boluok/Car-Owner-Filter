package com.example.carownersfilter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_owner")
data class CarOwner(
    @PrimaryKey
    val id:String,
    val first_name:String,
    val last_name:String,
    val email:String,
    val country:String,
    val car_model:String,
    val car_model_year:String,
    val car_color:String,
    val gender:String,
    val job_title:String,
    val bio:String
)