package com.example.carownersfilter.modules

import com.example.carownersfilter.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<CarOwnerRepository> { CarOwnerRepositoryImpl(carOwnerDao = get(),filterCarOwnerHelper = get()) }
    single <FiltersRepository>{ FiltersRepositoryImpl(carOwnersFilterAPI = get())  }

    single <FilterCarOwnerHelper>{ FilterCarOwnerHelperImpl()  }


}