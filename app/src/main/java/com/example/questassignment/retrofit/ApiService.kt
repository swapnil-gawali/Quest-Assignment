package com.example.questassignment.retrofit

import com.example.questassignment.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") id: String): UserResponse

}