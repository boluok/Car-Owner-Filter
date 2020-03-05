package com.example.carownersfilter.repository

import com.example.carownersfilter.model.CarOwner
import com.example.carownersfilter.model.Filters
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class FilterCarOwnerHelperImplTest {
    lateinit var listOfCarOwners:List<CarOwner>
    lateinit var filter:Filters
    val filterCarOwnerHelperImpl = FilterCarOwnerHelperImpl()
    @Before
    fun setUpList(){
        listOfCarOwners = listOf(CarOwner(id = "",first_name = "Bolu", last_name = "Okunaiya",job_title = "",car_color = "red",car_model_year = "",
            car_model = "",email = "",bio = "",country = "Nigeria",gender = "male"
            ))
        filter = Filters(avatar = "",createdAt = "",colors = listOf("Red"),countries = listOf("Nigeria"),fullName = "bolu Okunaiya",gender = "Male",id = "")
    }

    @Test
    fun getAllCarOwnersForFilter() {
        val result = filterCarOwnerHelperImpl.getAllCarOwnersForFilter(filter,listOfCarOwners)
        assert(result.isNotEmpty())
    }


}