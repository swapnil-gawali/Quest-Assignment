package com.example.questassignment.di

import com.example.questassignment.repo.NetworkRepo
import com.example.questassignment.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkRepoModule {

    @Provides
    @Singleton
    fun provideNetworkRepo(apiService: ApiService): NetworkRepo {
        return NetworkRepo(apiService)
    }

}