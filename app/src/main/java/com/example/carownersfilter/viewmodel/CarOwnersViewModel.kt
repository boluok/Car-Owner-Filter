package com.example.carownersfilter.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.carownersfilter.repository.CarOwnerRepository
import kotlinx.coroutines.launch

class CarOwnersViewModel(private  val carOwnerRepository: CarOwnerRepository) :BaseViewModel(){

    fun getAllCarOwners() =  carOwnerRepository.getAllCarOwners().asLiveData()


}