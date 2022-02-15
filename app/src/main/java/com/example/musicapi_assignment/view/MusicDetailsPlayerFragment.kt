package com.example.musicapi_assignment.view

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicapi_assignment.databinding.MusicDetailsPlayerBinding
import com.example.musicapi_assignment.model.TrackInformation
import com.squareup.picasso.Picasso

class MusicDetailsPlayerFragment : Fragment() {

    private lateinit var binding : MusicDetailsPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaURI : Uri

    companion object {
        const val DETAIL_TRACK = "DetailTrack"
        private const val TAG = "MusicDetailsPlayerFragment"
        /**
         * When the instance is created (When an item is selected) @see FragmentActivity.onMusicItemSelected
         * When an item is selected pass as argument the son selected
         */

        fun newInstance(track: TrackInformation): MusicDetailsPlayerFragment {
            return MusicDetailsPlayerFragment().apply {
                arguments = Bundle().apply{
                    putParcelable(DETAIL_TRACK, track)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicDetailsPlayerBinding.inflate(inflater, container,false)

        /**
         * If there are arguments set the image of the track,
         * create an object MediaPlayer and start to play the music.
         */
        arguments?.let { bundle ->
            bundle.getParcelable<TrackInformation>(DETAIL_TRACK)?.let {
                Picasso.get().load(it.artworkUrl100).into(binding.ivDetailsAlbumCover)
                mediaURI = Uri.parse(it.previewUrl)
                mediaPlayer = MediaPlayer.create(requireContext(), mediaURI)
                mediaPlayer.start()
            }
        }
        return binding.root
    }

    /**
     * When pressing back stop playing music
     */
    override fun onDestroyView() {
        if(mediaPlayer!=null){
            mediaPlayer.stop()
        }
        super.onDestroyView()
    }
}