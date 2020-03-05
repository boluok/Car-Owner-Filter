package com.example.carownersfilter.modules

import androidx.room.Room
import com.example.carownersfilter.local.room.CarOwnerDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModules = module {
    // Room Database instance
    single {  Room.databaseBuilder(androidApplication(), CarOwnerDatabase::class.java, "car_owner-db").
        fallbackToDestructiveMigration()
        .build() }

    //Dao
    val createAtStart = false
    single(createdAtStart = createAtStart) { get<CarOwnerDatabase>().carOwnerDao() }

}
