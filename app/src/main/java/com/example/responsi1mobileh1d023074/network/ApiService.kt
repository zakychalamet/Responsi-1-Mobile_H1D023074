package com.example.responsi1mobileh1d023074.network

import com.example.responsi1mobileh1d023074.model.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("v4/teams/{teamId}")
    suspend fun getTeam(
        @Header("X-Auth-Token") token: String,
        @Path("teamId") teamId: Int
    ): Response<Team>
}

