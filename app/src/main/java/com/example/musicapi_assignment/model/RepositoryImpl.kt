package com.example.musicapi_assignment.model

import com.example.musicapi_assignment.common.MusicAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl : Repository {

    override fun getSongs(term : String): Flow<UIState> {
        return flow {
            emit(UIState.LOADING())
            val response = MusicAPI.AppleMusicAPI.getTracksByTerm(term,MEDIA_TYPE,ENTITY_TYPE)

            if(response.isSuccessful){
                response.body()?.let {
                    emit(UIState.RESPONSE(it))
                    delay(500)
                    emit(UIState.LOADING(false))
                }
            }else{
                emit(UIState.ERROR("Problems with the server, try again later ${response.code()}"))
                delay(500)
                emit(UIState.LOADING(false))
            }
        }
    }

}