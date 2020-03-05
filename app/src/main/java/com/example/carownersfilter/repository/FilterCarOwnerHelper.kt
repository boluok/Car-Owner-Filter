package com.example.carownersfilter.repository

import com.example.carownersfilter.model.CarOwner
import com.example.carownersfilter.model.Filters


interface FilterCarOwnerHelper{
    fun getAllCarOwnersForFilter(filters: Filters, allCarOwners: List<CarOwner>):List<CarOwner>
}

class FilterCarOwnerHelperImpl():FilterCarOwnerHelper{

     override fun getAllCarOwnersForFilter(filters: Filters, allCarOwners: List<CarOwner>):List<CarOwner>{
        return allCarOwners.filterWith(filters)
    }

    fun List<CarOwner>.filterWith(filters: Filters):List<CarOwner>{
        return this.filter { hasSimilarFirstName(it,filters) && isTheSameGender(it,filters) && hasSameColors(it,filters) && hasTheSameCountries(it,filters) }
    }

    private fun hasTheSameCountries(carOwner: CarOwner, filters: Filters): Boolean {
        return !filters.colors.filter { it.equals(carOwner.country,true)  }.isNullOrEmpty()
    }

    private fun hasSameColors(carOwner: CarOwner, filters: Filters): Boolean {
        return !filters.colors.filter { it.equals(carOwner.car_color,true) }.isNullOrEmpty()
    }

    private fun isTheSameGender(carOwner: CarOwner, filter: Filters): Boolean {
        return carOwner.gender.equals(filter.gender,true)
    }

    private fun hasSimilarFirstName(carOwner: CarOwner, filter: Filters): Boolean {
        return containsName(carOwner.first_name,filter.fullName) && containsName(carOwner.last_name,filter.fullName)
    }

    private fun containsName(name: String, fullName: String): Boolean {
        return fullName.contains("${name}".toRegex())
    }


}
