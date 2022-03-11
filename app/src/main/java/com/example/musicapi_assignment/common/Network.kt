package com.example.musicapi_assignment.common

import com.example.musicapi_assignment.model.BASE_URL
import com.example.musicapi_assignment.model.IMusicAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    val AppleMusicAPI : IMusicAPI by lazy {
        initRetrofit().create(IMusicAPI::class.java)
    }

    private fun initRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}