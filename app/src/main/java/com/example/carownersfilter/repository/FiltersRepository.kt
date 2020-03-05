package com.example.carownersfilter.repository

import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.remote.CarOwnersFilterAPI
import com.example.carownersfilter.remote.UseCaseResult

interface FiltersRepository{
    suspend fun  getFiltersRemote():UseCaseResult<List<Filters>>
}


class FiltersRepositoryImpl(private val carOwnersFilterAPI: CarOwnersFilterAPI):BaseRepository(),FiltersRepository{
    override suspend fun getFiltersRemote(): UseCaseResult<List<Filters>> {
        return safeApiGETCall(carOwnersFilterAPI::getFiltersAsync,{!it.isNullOrEmpty()},{

        })
    }

}