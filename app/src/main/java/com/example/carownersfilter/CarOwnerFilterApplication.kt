package com.example.carownersfilter

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.carownersfilter.modules.dbModules
import com.example.carownersfilter.modules.networkModule
import com.example.carownersfilter.modules.repositoryModule
import com.example.carownersfilter.modules.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CarOwnerFilterApplication :MultiDexApplication(){
    private val LOG_TAG = "APP"


    override fun onCreate() {
        super.onCreate()
        initTimber()
        startKoin {
            androidContext(this@CarOwnerFilterApplication)
            modules(listOf(dbModules,networkModule,viewmodelModule,repositoryModule) )
        }


    }
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}