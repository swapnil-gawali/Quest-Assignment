package com.example.questassignment.util

sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Error(val errorMessage: String) : NetworkState<Nothing>()
    data class Success<T>(val data: T) : NetworkState<T>()
}