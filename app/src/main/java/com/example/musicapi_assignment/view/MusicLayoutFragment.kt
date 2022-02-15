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

/**
 * Music Fragment
 */
class MusicLayoutFragment : Fragment() {

    companion object{
        private const val MUSIC_TYPE = "classic"

        /**
         * Each instance requires an object of MusicResponse in order to display the music of the
         * selected category (the text of the tabs)
         *
         * @see TabLayoutFragment onTabSelected()
         */
        fun newInstance(musicResponse: MusicResponse) :MusicLayoutFragment{
            val fragment = MusicLayoutFragment()
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

        /**
         * Check the arguments received from the function newInstance() of this class inside the
         * companion object. If there are items proceed to execute the initView()
         */

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

        /**
         *Will iterate each element retrieved from the @param dataset  to create an Item of the
         * adapter and associate it with a TrackInformation object
         */

        binding.musicListView.adapter = MusicItemAdapter(dataset.results.map {
            TrackInformation(
                it.trackName,
                it.artistName,
                it.trackPrice,
                it.artworkUrl100,
                it.previewUrl
            )
        }){
            //onMusicItemSelected will be executed when an item is selected
            requireActivity().onMusicItemSelected(it)
        }
    }

    /**
     * When an item of the fragment is selected it will send the information of the
     * object (TrackInformation) selected to the MusicDetailsPlayerFragment
     */
    private fun FragmentActivity.onMusicItemSelected(trackInformation: TrackInformation){
        supportFragmentManager.beginTransaction().replace(android.R.id.content,
            MusicDetailsPlayerFragment.newInstance(trackInformation)).addToBackStack(null).commit()
    }
}