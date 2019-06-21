package com.hfad.myassignment.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    fun getRetrofit():Retrofit =Retrofit.Builder()
            .baseUrl(" https://gord6bd0zi.execute-api.ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}