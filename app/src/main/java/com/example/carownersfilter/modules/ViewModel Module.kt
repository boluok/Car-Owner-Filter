package com.example.carownersfilter.modules

import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.FiltersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewmodelModule = module{
    viewModel{ CarOwnersViewModel(carOwnerRepository = get()) }
    viewModel{ FiltersViewModel(filtersRepository = get()) }

}