package com.example.carownersfilter.viewmodel

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.carownersfilter.R
import com.example.carownersfilter.repository.CarOwnerRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarOwnersViewModel(private  val carOwnerRepository: CarOwnerRepository) :BaseViewModel(){

    fun getAllCarOwners() =  carOwnerRepository.getAllCarOwners().asLiveData()
    val onDataSaveSuccessful = SingleLiveEvent<FileStatus>()


     fun  getAndSaveCSV(context:Context){
        viewModelScope.launch {
            val csvData = withContext(IO){context.resources.openRawResource(R.raw.car_ownsers_data)}
            val rows: List<List<String>> = withContext(IO){csvReader().readAll(csvData)}
            println(rows[0])
            carOwnerRepository.saveCSCData(rows)
            onDataSaveSuccessful.value = FileStatus.FOUND
        }
    }


}

enum class FileStatus{
    COULD_NOT_FIND,FOUND
}