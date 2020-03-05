package com.example.carownersfilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.lifecycle.lifecycleScope
import com.example.carownersfilter.repository.CarOwnerRepository
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.observeChange
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.io.File

class MainActivity : AppCompatActivity() {
    private val carOwnerRepository:CarOwnerRepository by inject()
    private val carOwnerViewModel:CarOwnersViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val path = this.filesDir.absolutePath
        println(path)
       val fileDir =  this.getExternalFilesDir(null)
        println(fileDir?.absoluteFile)

      // val file = File("$path/decagon/car_ownsers_data.csv")


        lifecycleScope.launchWhenCreated {
//            val csv = getResources().openRawResource(R.raw.car_ownsers_data)
//            val rows: List<List<String>> = csvReader().readAll(csv)
//            carOwnerRepository.saveCSCData(rows)


        }


    }
}