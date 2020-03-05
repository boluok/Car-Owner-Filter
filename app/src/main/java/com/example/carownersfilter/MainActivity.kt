package com.example.carownersfilter


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.carownersfilter.model.CarOwner
import com.example.carownersfilter.repository.CarOwnerRepository
import com.example.carownersfilter.repository.FilterCarOwnerHelper
import com.example.carownersfilter.viewmodel.FiltersViewModel
import org.koin.android.ext.android.inject



class MainActivity : AppCompatActivity() {
    private val carOwnerRepository:CarOwnerRepository by inject()

    private val filtersViewModel:FiltersViewModel by inject()
    private val filterCarOwnerHelper:FilterCarOwnerHelper by inject()
    private lateinit var carOwnersList:List<CarOwner>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

//        val path = this.filesDir.absolutePath
//        println(path)
//       val fileDir =  this.getExternalFilesDir(null)
//        println(fileDir?.absoluteFile)
//
//      // val file = File("$path/decagon/car_ownsers_data.csv")
//
//        carOwnerViewModel.getAllCarOwners().observeChange(this@MainActivity){
//            carOwnersList = it
//            filtersViewModel.getAllFiltersAPI()
//        }
//        filtersViewModel.allFiltersResponse.observeChange(this@MainActivity){
//            Timber.d("FOR THESE FILTER ${it[1]} I GOT \n ${filterCarOwnerHelper.getAllCarOwnersForFilter(it[1],carOwnersList).takeLast(3)}")
//        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar, menu)
        return true
    }
}