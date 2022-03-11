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
class MusicItemAdapter(private val dataset : List<TrackInformation>,
                       private val onMusicItemSelected : (TrackInformation) -> Unit)
                        : RecyclerView.Adapter<MusicViewHolder>() {

    fun setListOfSongs(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder(
            /**
             * Each item will be an element of ItemLayoutBinding
             * @see item_layout.xml
             * if you command+Click the object ItemLayoutBinding it will open the xml
             */
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        /**
         * required to perform the click action on each element
         */
        holder.onBind(dataset[position]){
            onMusicItemSelected(it)
        }
    }

    /**
     * required method to implement the adapter
     */
    override fun getItemCount(): Int = dataset.size

}