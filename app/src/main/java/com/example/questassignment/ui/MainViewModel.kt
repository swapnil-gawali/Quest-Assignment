package com.example.questassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questassignment.model.UserResponse
import com.example.questassignment.repo.NetworkRepo
import com.example.questassignment.util.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkRepo: NetworkRepo) : ViewModel() {

    private var _userResponse = MutableLiveData<NetworkState<UserResponse>>()
    val userResponse: LiveData<NetworkState<UserResponse>> get() = _userResponse

    fun getUserDetails(id: String) = viewModelScope.launch {
        _userResponse.postValue(NetworkState.Loading)

        val userDetails = networkRepo.getUserDetails(id)
        _userResponse.postValue(userDetails)
    }
}