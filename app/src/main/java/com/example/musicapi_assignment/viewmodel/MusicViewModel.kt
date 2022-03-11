package com.example.musicapi_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapi_assignment.common.Network
import com.example.musicapi_assignment.model.CLASSIC_TERM
import com.example.musicapi_assignment.model.ENTITY_TYPE
import com.example.musicapi_assignment.model.MEDIA_TYPE
import com.example.musicapi_assignment.model.MusicResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MusicViewModel"

class MusicViewModel : ViewModel(){

    private lateinit var job : Job
    private val error = MutableLiveData<String>()
    private lateinit var currentCategory : String

    val songs: MutableLiveData<MusicResponse> by lazy {
        MutableLiveData<MusicResponse>().also {
            getTracksBySearchTerm()
        }
    }

    fun getSongs(term : String = CLASSIC_TERM) : LiveData<MusicResponse>{
        if(!this::currentCategory.isInitialized){
            return songs
        }
        if (term != currentCategory){
            getTracksBySearchTerm(term)
        }
        return songs
    }

    private fun getTracksBySearchTerm(term : String = CLASSIC_TERM) {
        job = CoroutineScope(Dispatchers.IO).launch{
            Network.AppleMusicAPI.getTracksByTerm(term, MEDIA_TYPE, ENTITY_TYPE).enqueue(
                object : Callback<MusicResponse>{
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>,
                    ) {
                        if(response.isSuccessful){
                            currentCategory = term
                            songs.postValue(response.body())
                            Log.d(TAG, "API call: ")
                        }else{
                            Log.d(TAG, "onResponse: ${call.toString()}")
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        error.postValue(t.message)
                    }
                }
            )
        }
    }
}