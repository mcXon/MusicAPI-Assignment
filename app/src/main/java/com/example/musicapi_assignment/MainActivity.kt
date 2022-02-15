package com.example.musicapi_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.musicapi_assignment.databinding.ActivityMainBinding
import com.example.musicapi_assignment.model.*
import com.example.musicapi_assignment.view.MusicLayoutFragment
import com.example.musicapi_assignment.view.TabLayoutFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityMainBinding
private const val TAG = "MainActivity"

/**
 * activity_main is divided by 2 Fragment Layouts
 *
 * 1st will be the music_tab_Layout.xml (Kotlin class assigned to the view : TabLayoutFragment)
 * 2nd will be the music_details_frame_layout.xml (Kotlin class assigned to the view : MusicLayoutFragment)
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //Bind the tab_layout to the first Fragment Layout
        supportFragmentManager.beginTransaction().replace(R.id.music_tab_layout, TabLayoutFragment()).commit()

        //As the first element is the Classic music -> execute the search for "classic"
        executeRetrofit(CLASSIC_TERM)
        setContentView(binding.root)

    }

    /**
     * @param String (word to search in the API)
     */
    fun executeRetrofit(searchTerm : String){
        //with the instance of retrofit perfotm the search with the term passed
        IMusicAPI.initRetrofit().getClassicTracks(searchTerm, MEDIA_TYPE, ENTITY_TYPE).enqueue(
            object : Callback<MusicResponse>{
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>,
                ) {
                    if(response.isSuccessful) {
                        //If the response is correct we proceed to inflate the MusicLayoutFragment
                        Log.d(TAG, "Retrofit onResponse: ${response.body()}")
                        inflateMusicFragment(response.body())
                    }
                    else
                        Log.d(TAG, "onResponse: something happen")
                }
                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.e(TAG, "Retrofit onFailure: ")
                }
            }
        )
    }

    private fun inflateMusicFragment(dataset : MusicResponse?){
        dataset?.let {
            //We create a new instance of MusicLayoutFragment so we can pass the response of retrofit
            supportFragmentManager.beginTransaction().replace(R.id.music_items_layout,
                MusicLayoutFragment.newInstance(it)).commit()
        }
    }
}