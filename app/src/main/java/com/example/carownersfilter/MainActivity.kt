package com.example.carownersfilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.lifecycle.lifecycleScope
import com.example.carownersfilter.model.CarOwner
import com.example.carownersfilter.repository.CarOwnerRepository
import com.example.carownersfilter.repository.FilterCarOwnerHelper
import com.example.carownersfilter.repository.FilterCarOwnerHelperImpl
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.FiltersViewModel
import com.example.carownersfilter.viewmodel.observeChange
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.io.File

class MainActivity : AppCompatActivity() {
    private val carOwnerRepository:CarOwnerRepository by inject()
    private val carOwnerViewModel:CarOwnersViewModel by inject()
    private val filtersViewModel:FiltersViewModel by inject()
    private val filterCarOwnerHelper:FilterCarOwnerHelper by inject()
    private lateinit var carOwnersList:List<CarOwner>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val path = this.filesDir.absolutePath
        println(path)
       val fileDir =  this.getExternalFilesDir(null)
        println(fileDir?.absoluteFile)

      // val file = File("$path/decagon/car_ownsers_data.csv")

        carOwnerViewModel.getAllCarOwners().observeChange(this@MainActivity){
            carOwnersList = it
            filtersViewModel.getAllFiltersAPI()
        }
        filtersViewModel.allFiltersResponse.observeChange(this@MainActivity){
            Timber.d("FOR THESE FILTER ${it[1]} I GOT \n ${filterCarOwnerHelper.getAllCarOwnersForFilter(it[1],carOwnersList).takeLast(3)}")
        }


    }
}