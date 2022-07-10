package com.example.musicapi_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicapi_assignment.MainActivity
import com.example.musicapi_assignment.model.CLASSIC_TERM
import com.example.musicapi_assignment.model.HARD_ROCK
import com.example.musicapi_assignment.model.J_CASH
import com.example.musicapi_assignment.R
import com.example.musicapi_assignment.databinding.MusicTabLayoutBinding
import com.example.musicapi_assignment.viewmodel.MusicViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout

private const val TAG = "TabLayoutFragment"

class TabLayoutFragment : Fragment() {

    private lateinit var binding: MusicTabLayoutBinding
    lateinit var bottomTabLayout : BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicTabLayoutBinding.inflate(inflater, container,false)

        bottomTabLayout = binding.musicTabsCategory
        val viewModel = ViewModelProvider(requireActivity()).get(MusicViewModel::class.java)
        bottomTabLayout.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_item_classic -> {
                    viewModel.getSongs(CLASSIC_TERM)
                    true
                }
                R.id.tab_item_hard_rock -> {
                    viewModel.getSongs(HARD_ROCK)
                    true
                }
                R.id.tab_item_cash -> {
                    viewModel.getSongs(J_CASH)
                    true
                }
                else -> false

            }
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


}

