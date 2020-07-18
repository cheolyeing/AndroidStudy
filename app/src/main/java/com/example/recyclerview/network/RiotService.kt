package com.example.recyclerview.network

import com.example.recyclerview.rest.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RiotService {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): ApiService = retrofit.create(ApiService::class.java)
}