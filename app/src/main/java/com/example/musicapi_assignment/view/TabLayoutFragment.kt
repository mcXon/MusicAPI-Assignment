package com.example.musicapi_assignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicapi_assignment.MainActivity
import com.example.musicapi_assignment.databinding.MusicTabLayoutBinding
import com.google.android.material.tabs.TabLayout

private const val TAG = "TabLayoutFragment"

class TabLayoutFragment : Fragment() {

    private lateinit var binding: MusicTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicTabLayoutBinding.inflate(inflater, container,false)

        val tabLayout = binding.musicTabsCategory

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabSelected:")
                if(requireActivity() is MainActivity)
                    (requireActivity() as MainActivity).executeRetrofit(tab?.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return binding.root
    }
}