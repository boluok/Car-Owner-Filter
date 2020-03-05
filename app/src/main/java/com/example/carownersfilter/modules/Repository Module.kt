package com.example.carownersfilter.modules

import com.example.carownersfilter.repository.CarOwnerRepository
import com.example.carownersfilter.repository.CarOwnerRepositoryImpl
import com.example.carownersfilter.repository.FiltersRepository
import com.example.carownersfilter.repository.FiltersRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<CarOwnerRepository> { CarOwnerRepositoryImpl(carOwnerDao = get()) }
    single <FiltersRepository>{ FiltersRepositoryImpl(carOwnersFilterAPI = get())  }


}