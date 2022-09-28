package com.example.questassignment.repo

import com.example.questassignment.model.UserResponse
import com.example.questassignment.retrofit.ApiService
import com.example.questassignment.util.NetworkState

class NetworkRepo(private val apiService: ApiService) {

    suspend fun getUserDetails(id: String): NetworkState<UserResponse> {
        return try {
            NetworkState.Success(apiService.getUserDetails(id))
        } catch (e: Exception) {
            NetworkState.Error(e.message ?: "Something went wrong, please try later")
        }
    }
}