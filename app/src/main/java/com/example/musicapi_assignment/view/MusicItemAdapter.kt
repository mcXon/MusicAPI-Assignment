package com.example.musicapi_assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi_assignment.databinding.ItemLayoutBinding
import com.example.musicapi_assignment.model.TrackInformation


/**
 * Adapter class linked to the recycler view
 *  This is assigned in the Class MusicLayoutFragment ln 75
 */
class MusicItemAdapter(private var dataset : List<TrackInformation>
//, private val onMusicItemSelected : (TrackInformation) -> Unit
) : RecyclerView.Adapter<MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        /**
         * required to perform the click action on each element
         */
        holder.onBind(dataset[position])
    }

    /**
     * required method to implement the adapter
     */
    override fun getItemCount(): Int = dataset.size

    fun updateDisplayingSongs(results: List<TrackInformation>) {
        dataset = results
        notifyDataSetChanged()
    }
}