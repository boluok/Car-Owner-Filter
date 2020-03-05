package com.example.carownersfilter.modules

import com.example.carownersfilter.BuildConfig
import com.example.carownersfilter.remote.CarOwnersFilterAPI
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CAR_OWNERS_FILTER_BASE_URL = "https://android-json-test-api.herokuapp.com/"



val networkModule = module{
    single {
        createWebService<CarOwnersFilterAPI>(RxJava2CallAdapterFactory.create(), CAR_OWNERS_FILTER_BASE_URL)
    }

}




/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient())
        .build()
    return retrofit.create(T::class.java)
}

fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

}

fun okHttpClient() =   OkHttpClient.Builder()
    .addInterceptor(headersInterceptor())
    .addInterceptor(loggingInterceptor())
    .readTimeout(5, TimeUnit.MINUTES)
    .connectTimeout(  5, TimeUnit.MINUTES)
    .writeTimeout(5, TimeUnit.MINUTES)
    .build()

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
        .addHeader("Content-Type", "application/json")
        .build())
}
