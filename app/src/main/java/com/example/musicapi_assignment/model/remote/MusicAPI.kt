package com.example.musicapi_assignment.common

import com.example.musicapi_assignment.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    //Request to get songs information
    @GET(ENDPOINT)
    suspend fun getTracksByTerm(@Query(TERM) term : String,
                        @Query(MEDIA) media : String,
                        @Query(ENTITY) entity : String,
                        @Query(LIMIT) limit: Int = 50) : Response<MusicResponse>

    companion object{
        val AppleMusicAPI : MusicAPI by lazy {
            initRetrofit().create(MusicAPI::class.java)
        }

        private fun initRetrofit() : Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }


}