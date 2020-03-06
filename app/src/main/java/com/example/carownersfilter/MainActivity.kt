package com.example.carownersfilter


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar, menu)
        return true
    }




}
fun NavController.navigateUpOrFinish(activity: AppCompatActivity): Boolean {
    return if (navigateUp()) {
        true
    } else {
        activity.finish()
        true
    }
}
