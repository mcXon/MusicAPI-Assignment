package com.example.musicapi_assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi_assignment.databinding.ItemLayoutBinding
import com.example.musicapi_assignment.model.TrackInformation

class MusicItemAdapter(private val dataset : List<TrackInformation>,
                       private val onMusicItemSelected : (TrackInformation) -> Unit)
                        : RecyclerView.Adapter<MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(dataset[position]){
            onMusicItemSelected(it)
        }
    }

    override fun getItemCount(): Int = dataset.size

}