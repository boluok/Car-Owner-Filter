package com.example.carownersfilter.viewmodel

import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.repository.FiltersRepository

class FiltersViewModel (private val filtersRepository: FiltersRepository):BaseViewModel(){
    val allFiltersResponse = SingleLiveEvent<List<Filters>>()


    fun getAllFiltersAPI(){
        apiRequestGET(filtersRepository::getFiltersRemote,allFiltersResponse,{"Something went wrong"},{})
    }
}