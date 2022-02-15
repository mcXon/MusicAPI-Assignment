package com.example.musicapi_assignment.model

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IMusicAPI {

    //This is not used
    @GET(ENDPOINT)
    fun getClassicMusic() : Observable<List<MusicResponse>>

    //Request to get songs information
    @GET(ENDPOINT)
    fun getClassicTracks(@Query(TERM) term : String,
                         @Query(MEDIA) media : String,
                         @Query(ENTITY) entity : String,
                         @Query(LIMIT) limit: Int = 50) : Call<MusicResponse>

    //Singleton to initialize retrofit
    companion object{
        fun initRetrofit(): IMusicAPI{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IMusicAPI::class.java)
        }
    }
}