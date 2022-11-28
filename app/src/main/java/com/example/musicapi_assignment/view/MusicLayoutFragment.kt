package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapi_assignment.databinding.MusicDetailsFrameLayoutBinding
import com.example.musicapi_assignment.model.MusicResponse
import com.example.musicapi_assignment.model.TrackInformation
import com.example.musicapi_assignment.model.UIState
import com.example.musicapi_assignment.viewmodel.MusicViewModel


/**
 * Music Fragment
 */
class MusicLayoutFragment : Fragment() {

    /**
     * Create instance of viewModel
     */
    val viewModel : MusicViewModel by activityViewModels()
    private lateinit var binding : MusicDetailsFrameLayoutBinding

    private val adapter : MusicItemAdapter by lazy {
        MusicItemAdapter(emptyList())
    }

    companion object{
        fun newInstance() :MusicLayoutFragment{
            return  MusicLayoutFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicDetailsFrameLayoutBinding.inflate(inflater, container, false)
        initObservables()
        initView()
        return binding.root
    }

    /**
     * @param dataset Music retrieved from the API call
     */
    private fun initView(){
        binding.musicListView.layoutManager = LinearLayoutManager(context)
        binding.musicListView.adapter = adapter
    }

    /**
     * When an item of the fragment is selected it will send the information of the
     * object (TrackInformation) selected to the MusicDetailsPlayerFragment
     */
    private fun FragmentActivity.onMusicItemSelected(trackInformation: TrackInformation){
        supportFragmentManager.beginTransaction().replace(android.R.id.content,
            MusicDetailsPlayerFragment.newInstance(trackInformation)).addToBackStack(null).commit()
    }

    private fun initObservables(){
        viewModel.songs.observe(viewLifecycleOwner){ state ->
            when (state){
                is UIState.RESPONSE -> updateSongs(state.songs)
                is UIState.LOADING -> showLoading(state.isLoading)
                is UIState.ERROR -> showError(state.errorResponse)
            }
        }
    }

    private fun showError(errorResponse: String) {
        //TODO implement error screen?

    }

    private fun updateSongs(songs: MusicResponse) {
        adapter.updateDisplayingSongs(songs.results)
    }

    private fun showLoading(loading: Boolean) {
        //TODO implement Loading screen
    }
}