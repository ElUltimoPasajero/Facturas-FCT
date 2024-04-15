package com.example.facturas_tfc.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
         return Retrofit.Builder()
             .baseUrl("https://viewnextandroid.wiremockapi.cloud/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()


    }
}

