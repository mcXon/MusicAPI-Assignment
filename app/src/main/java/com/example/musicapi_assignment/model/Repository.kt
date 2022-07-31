package com.example.musicapi_assignment.model

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getSongs(term : String =  CLASSIC_TERM) : Flow<UIState>
}