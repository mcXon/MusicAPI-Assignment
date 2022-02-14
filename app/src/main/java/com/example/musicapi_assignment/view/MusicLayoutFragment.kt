package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapi_assignment.MainActivity
import com.example.musicapi_assignment.databinding.MusicDetailsFrameLayoutBinding
import com.example.musicapi_assignment.model.MusicResponse
import com.example.musicapi_assignment.model.TrackInformation


private const val TAG = "ClassicMusicLayout"

class ClassicMusicLayoutFragment : Fragment() {

    companion object{
        private const val MUSIC_TYPE = "classic"
        fun newInstance(musicResponse: MusicResponse) :ClassicMusicLayoutFragment{
            val fragment = ClassicMusicLayoutFragment()
            val bundle = Bundle()
            bundle.putParcelable(MUSIC_TYPE, musicResponse)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding : MusicDetailsFrameLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicDetailsFrameLayoutBinding.inflate(inflater, container, false)

        arguments?.let { bundle ->
            bundle.getParcelable<MusicResponse>(MUSIC_TYPE)?.let {
                initView(it)
            }
        }
        return binding.root
    }

    /**
     * @param dataset Music retrieved from the API call
     */
    private fun initView(dataset: MusicResponse){
        binding.musicListView.layoutManager = LinearLayoutManager(context)

        binding.musicListView.adapter = MusicItemAdapter(dataset.results.map {
            TrackInformation(
                it.trackName,
                it.artistName,
                it.trackPrice,
                it.artworkUrl100,
                it.previewUrl
            )
        }){
            requireActivity().onMusicItemSelected(it)
        }
    }

    private fun FragmentActivity.onMusicItemSelected(trackInformation: TrackInformation){
        supportFragmentManager.beginTransaction().replace(android.R.id.content,
            MusicDetailsPlayerFragment.newInstance(trackInformation)).addToBackStack(null).commit()
    }
}