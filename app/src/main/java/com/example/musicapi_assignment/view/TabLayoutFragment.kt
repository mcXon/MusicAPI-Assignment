package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicapi_assignment.MainActivity
import com.example.musicapi_assignment.databinding.MusicTabLayoutBinding
import com.example.musicapi_assignment.viewmodel.MusicViewModel
import com.google.android.material.tabs.TabLayout

private const val TAG = "TabLayoutFragment"

class TabLayoutFragment : Fragment() {

    private lateinit var binding: MusicTabLayoutBinding
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicTabLayoutBinding.inflate(inflater, container,false)

        tabLayout = binding.musicTabsCategory
        val viewModel = ViewModelProvider(requireActivity()).get(MusicViewModel::class.java)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(requireActivity() is MainActivity) //If the activity is MainActivity
                        //run MainActivity.executeRetrofit() with the tab text
                        // see music_tab_layout.xml (android:text) of each element
                    viewModel.getSongs(tab?.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return binding.root
    }
}