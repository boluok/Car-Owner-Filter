package com.example.carownersfilter.viewmodel

import androidx.lifecycle.*
import com.example.carownersfilter.remote.UseCaseResult
import com.example.carownersfilter.utils.handleException

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel() {


    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()



    fun validateInput(vararg data:Pair<String,String>):Boolean{
        var error = ""
        data.forEach { field ->
            if(field.first.isNullOrEmpty()){
                if(error == "") error += "${field.second} is missing" else  ", ${field.second} is missing"
            }
        }
        if(error != ""){
            showError.value = error
            return false
        }
        return true
    }

    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String){
        showLoading.value = true
        viewModelScope.launch {
            val response = withContext(IO){apiCall(request)}
            showLoading.value = false
            when(response){
                is UseCaseResult.Success -> observer.value = response.data
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }
    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String,
                                 onSuccessOperations:(response:T)->Unit){
        showLoading.value = true
        viewModelScope.launch {
            val response = withContext(IO){apiCall(request)}
            showLoading.value = false
            when(response){
                is UseCaseResult.Success -> {
                    observer.value = response.data
                    onSuccessOperations(response.data)
                }
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }

    fun <T:Any> apiRequestGET( apiCall:suspend ()-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String,
                                 onSuccessOperations:(response:T)->Unit){
        showLoading.value = true
        viewModelScope.launch {
            val response = withContext(IO){apiCall()}
            showLoading.value = false
            when(response){
                is UseCaseResult.Success -> {
                    observer.value = response.data
                    onSuccessOperations(response.data)
                }
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }
}

fun<T:Any> LiveData<T>.observeChange(owner: LifecycleOwner, action:(T)->Unit){
    this.observe(owner,androidx.lifecycle.Observer { action(it) })
}

