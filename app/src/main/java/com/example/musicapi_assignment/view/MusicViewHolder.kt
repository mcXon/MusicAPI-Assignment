package com.example.musicapi_assignment.view

import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi_assignment.R
import com.example.musicapi_assignment.databinding.ItemLayoutBinding
import com.example.musicapi_assignment.model.TrackInformation
import com.squareup.picasso.Picasso


const val FREE = "FREE"

class MusicViewHolder(private val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    fun onBind(trackInformation: TrackInformation, callBack: (TrackInformation)->Unit){
        binding.tvSongName.text = trackInformation.trackName
        binding.tvSongArtist.text = trackInformation.artistName
        if(trackInformation.trackPrice > 0){
            binding.tvSongPrice.text = binding.root.context.getString(R.string.currency, trackInformation.trackPrice.toString())
        }else{
            binding.tvSongPrice.text = FREE
        }
        Picasso.get().load(trackInformation.artworkUrl100).into(binding.ivSongAlbum)


        //add listener
        binding.root.setOnClickListener{
            callBack(trackInformation)
        }
    }
}