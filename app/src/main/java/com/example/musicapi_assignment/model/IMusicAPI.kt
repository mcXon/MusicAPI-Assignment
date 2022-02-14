package com.example.musicapi_assignment.model

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IMusicAPI {
    @GET(ENDPOINT)
    fun getClassicMusic() : Observable<List<MusicResponse>>

    @GET(ENDPOINT)
    fun getClassicTracks(@Query(TERM) term : String,
                         @Query(MEDIA) media : String,
                         @Query(ENTITY) entity : String,
                         @Query(LIMIT) limit: Int = 50) : Call<MusicResponse>

    companion object{
        fun initRetrofit(): IMusicAPI{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IMusicAPI::class.java)
        }
    }
}