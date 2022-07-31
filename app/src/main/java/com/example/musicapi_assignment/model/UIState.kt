package com.example.musicapi_assignment.model

sealed class UIState{
    data class RESPONSE(val songs : MusicResponse) : UIState()
    data class ERROR(val errorResponse : String) : UIState()
    data class LOADING(val isLoading : Boolean = true) : UIState()

}
