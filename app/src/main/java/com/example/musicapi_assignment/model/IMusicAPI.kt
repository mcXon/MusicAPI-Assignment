package com.example.musicapi_assignment.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMusicAPI {

    //Request to get songs information
    @GET(ENDPOINT)
    fun getTracksByTerm(@Query(TERM) term : String,
                         @Query(MEDIA) media : String,
                         @Query(ENTITY) entity : String,
                         @Query(LIMIT) limit: Int = 50) : Call<MusicResponse>

}