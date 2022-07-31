package com.example.musicapi_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapi_assignment.di.Provider
import com.example.musicapi_assignment.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "MusicViewModel"

class MusicViewModel : ViewModel(){

    private val repository : Repository by lazy {
        Provider.provideRepository()
    }

    private val _songs = MutableLiveData<UIState>()

    val songs : LiveData<UIState>
    get() = _songs

    init {
        viewModelScope.launch {
            requestSongs()
        }
    }

    suspend fun requestSongs(term : String = CLASSIC_TERM) {
        repository.getSongs(term).collect(){ uiState->
            _songs.postValue(uiState)
        }
    }
}