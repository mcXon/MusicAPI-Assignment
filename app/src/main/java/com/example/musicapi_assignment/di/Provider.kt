package com.example.musicapi_assignment.di

import com.example.musicapi_assignment.model.Repository
import com.example.musicapi_assignment.model.RepositoryImpl

object Provider {
    fun provideRepository() : Repository = RepositoryImpl()
}