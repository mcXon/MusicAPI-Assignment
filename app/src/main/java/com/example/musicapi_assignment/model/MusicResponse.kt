package com.example.musicapi_assignment.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicResponse(
    val resultCount : Int,
    val results: List<TrackInformation>
) : Parcelable

@Parcelize
data class TrackInformation(
    val trackName : String,
    val artistName : String,
    val trackPrice : Double,
    val artworkUrl100 : String,
    val previewUrl : String
) : Parcelable
