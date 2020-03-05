package com.example.carownersfilter.repository

import com.example.carownersfilter.local.room.dao.CarOwnerDao
import com.example.carownersfilter.model.CarOwner
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber

interface CarOwnerRepository {
     fun getAllCarOwners():Flow<List<CarOwner>>
    suspend fun updateCarOwnersDB(carOwners: List<CarOwner>)
    suspend fun saveCSCData(csvData:List<List<String>>)
}

class CarOwnerRepositoryImpl(private val carOwnerDao: CarOwnerDao):CarOwnerRepository{
    override  fun getAllCarOwners(): Flow<List<CarOwner>> {
        return carOwnerDao.getAllCarOwners()
    }

    override suspend fun updateCarOwnersDB(carOwners: List<CarOwner>) {
        withContext(IO){
            carOwnerDao.updateCarOwners(carOwners)
        }
    }

    override suspend fun saveCSCData(csvData: List<List<String>>) {
        val listOfCarOwners = csvData.map { parseRow(it) }.filterNotNull()
        Timber.d("Car Owners List ${listOfCarOwners.takeLast(5)}")
        updateCarOwnersDB(listOfCarOwners)
    }

    private  fun parseRow(row: List<String>): CarOwner? {
        require(row.size == 11){return null}
        return CarOwner(id = row[0],first_name = row[1],last_name = row[2],email = row[3],country = row[4],
            car_model = row[5],car_model_year = row[6],car_color = row[7],gender = row[8],job_title = row[9],bio = row[10]
            )
    }

}