package com.example.recyclerview.rest

import com.example.recyclerview.dto.Summoner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("X-Riot-Token: RGAPI-0d02e9b0-167a-4ce1-a352-715515f1fbea")
    @GET("lol/summoner/v4/summoners/by-name/{summonerName}")

    fun requestSummonerInfo(@Path("summonerName") summonerName: String): Call<Summoner>
}