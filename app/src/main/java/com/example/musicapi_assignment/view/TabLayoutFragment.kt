package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.example.musicapi_assignment.databinding.MusicTabLayoutBinding
import com.example.musicapi_assignment.viewmodel.MusicViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

private const val TAG = "TabLayoutFragment"

class TabLayoutFragment : Fragment() {

    private lateinit var binding: MusicTabLayoutBinding
    lateinit var bottomTabLayout : BottomNavigationView

    private val viewModel : MusicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicTabLayoutBinding.inflate(inflater, container,false)
        bottomTabLayout = binding.musicTabsCategory
        bottomTabLayout.setOnItemSelectedListener { item ->
            viewModel.onclickAction(item.title.toString())
            true
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAG, bottomTabLayout.selectedItemId)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getInt(TAG)?.let {
            bottomTabLayout.selectedItemId = it
        }
    }

    private fun MusicViewModel.onclickAction(term : String){
        viewModel.viewModelScope.launch {
            viewModel.requestSongs(term)
        }
    }
}


