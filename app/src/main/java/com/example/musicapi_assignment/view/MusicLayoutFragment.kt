package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapi_assignment.databinding.MusicDetailsFrameLayoutBinding
import com.example.musicapi_assignment.model.CLASSIC_TERM
import com.example.musicapi_assignment.model.TrackInformation
import com.example.musicapi_assignment.viewmodel.MusicViewModel


/**
 * Music Fragment
 */
class MusicLayoutFragment : Fragment() {

    /**
     * Create instance of viewModel
     */
    private val vModel : MusicViewModel by lazy{
        ViewModelProvider(requireActivity(),
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MusicViewModel() as T
                }
            })[MusicViewModel::class.java]
    }

    companion object{
        private const val MUSIC_TYPE = "classic"
        fun newInstance() :MusicLayoutFragment{
            val fragment = MusicLayoutFragment()
            val bundle = Bundle()
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

        initView(vModel)
        return binding.root
    }

    /**
     * @param dataset Music retrieved from the API call
     */
    private fun initView(vModel: MusicViewModel){
        getSongsFromViewModel(CLASSIC_TERM, vModel)
        binding.musicListView.layoutManager = LinearLayoutManager(context)
    }

    /**
     * When an item of the fragment is selected it will send the information of the
     * object (TrackInformation) selected to the MusicDetailsPlayerFragment
     */
    private fun FragmentActivity.onMusicItemSelected(trackInformation: TrackInformation){
        supportFragmentManager.beginTransaction().replace(android.R.id.content,
            MusicDetailsPlayerFragment.newInstance(trackInformation)).addToBackStack(null).commit()
    }

    private fun getSongsFromViewModel(searchTerm : String, vModel: MusicViewModel){
        vModel.getSongs().observe(viewLifecycleOwner, Observer {dataset->
//            Log.d(TAG, "executeRetrofit: $dataset")
            val musicItemAdapter = MusicItemAdapter(dataset.results.map {
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
            binding.musicListView.adapter = musicItemAdapter
        })
    }
}