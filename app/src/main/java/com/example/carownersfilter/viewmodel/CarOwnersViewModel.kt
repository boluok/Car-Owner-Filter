package com.example.carownersfilter.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.carownersfilter.R
import com.example.carownersfilter.local.room.PaperPrefs
import com.example.carownersfilter.local.room.getBooleanPref
import com.example.carownersfilter.local.room.savePref
import com.example.carownersfilter.model.CarOwner
import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.repository.CarOwnerRepository
import com.example.carownersfilter.utils.CheckPermissionUtil
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarOwnersViewModel(private  val carOwnerRepository: CarOwnerRepository,private val paperPrefs: PaperPrefs) :BaseViewModel(){

    fun getAllCarOwners() =  carOwnerRepository.getAllCarOwners().asLiveData()
    val onDataSaveSuccessful = SingleLiveEvent<FileStatus>()
    var allCarOwners = listOf<CarOwner>()


    init {
        viewModelScope.launch {
            allCarOwners = carOwnerRepository.getAllCarOwnersList()
        }

    }

     fun  getAndSaveCSV(context:Context){
        viewModelScope.launch {
            val csvData = withContext(IO){context.resources.openRawResource(R.raw.car_ownsers_data)}
            val rows: List<List<String>> = withContext(IO){csvReader().readAll(csvData)}
            allCarOwners =  carOwnerRepository.saveCSCData(rows)
            paperPrefs.savePref(PaperPrefs.DATALOADED,true)
            onDataSaveSuccessful.value = FileStatus.FOUND
        }
    }


    fun getCarOwnersForFilter(filters: Filters):List<CarOwner>{
        return carOwnerRepository.getAllCarOwnersForFilter(filters,allCarOwners)
    }

    fun getStatus(context:Context):UserStatus{
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            return UserStatus.NO_PERMISSION
        }
        return if(paperPrefs.getBooleanPref(PaperPrefs.DATALOADED,false)){
            UserStatus.PERMISSION_GRANTED_DATA_LOADED
        }else{
            UserStatus.PERMISSION_GRANTED_NO_FILE
        }
    }





}

enum class FileStatus{
    COULD_NOT_FIND,FOUND
}
enum class UserStatus{
    PERMISSION_GRANTED_DATA_LOADED,PERMISSION_GRANTED_NO_FILE,NO_PERMISSION
}