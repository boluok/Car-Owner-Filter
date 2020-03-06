package com.example.carownersfilter.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
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
import java.io.File
import java.io.InputStream

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
            val csvFile = File(  "${Environment.getExternalStorageDirectory()}/decagon/car_ownsers_data.csv")
            if(csvFile.exists()){
                val rows: List<List<String>> = withContext(IO){csvReader().readAll(csvFile)}
                allCarOwners =  carOwnerRepository.saveCSCData(rows)
                paperPrefs.savePref(PaperPrefs.DATALOADED,true)
                showLoading.value = false
                onDataSaveSuccessful.value = FileStatus.FOUND
            }else{
                onDataSaveSuccessful.value = FileStatus.COULD_NOT_FIND
            }
        }
    }

    fun  getAndSaveCSV(file:InputStream){
        viewModelScope.launch {
            showLoading.value = true
            val rows: List<List<String>> = withContext(IO){csvReader().readAll(file)}
            allCarOwners =  carOwnerRepository.saveCSCData(rows)
            paperPrefs.savePref(PaperPrefs.DATALOADED,true)
            showLoading.value = false
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