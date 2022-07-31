package com.example.musicapi_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapi_assignment.databinding.ActivityMainBinding
import com.example.musicapi_assignment.view.MusicLayoutFragment
import com.example.musicapi_assignment.view.TabLayoutFragment
import com.google.android.material.tabs.TabLayout

private lateinit var binding: ActivityMainBinding
private const val TAG = "MainActivity"

/**
 * activity_main is divided by 2 Fragment Layouts
 *
 * 1st will be the music_tab_Layout.xml (Kotlin class assigned to the view : TabLayoutFragment)
 * 2nd will be the music_details_frame_layout.xml (Kotlin class assigned to the view : MusicLayoutFragment)
 *
 */

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout : TabLayoutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabLayout = TabLayoutFragment()
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Bind the tab_layout to the first Fragment Layout
        supportFragmentManager.beginTransaction().replace(R.id.music_tab_layout, tabLayout).commit()

        //As the first element is the Classic music -> execute the search for "classic"
        setContentView(binding.root)
        inflateMusicFragment()
    }


    /**
     * @param String (word to search in the API)
     */
    private fun inflateMusicFragment(){
            //We create a new instance of MusicLayoutFragment so we can pass the response of retrofit
            supportFragmentManager.beginTransaction().replace(R.id.music_items_layout,
                MusicLayoutFragment.newInstance()).commit()
    }
}