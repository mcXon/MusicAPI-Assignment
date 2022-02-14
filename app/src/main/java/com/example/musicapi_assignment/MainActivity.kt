package com.example.musicapi_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.musicapi_assignment.databinding.ActivityMainBinding
import com.example.musicapi_assignment.model.*
import com.example.musicapi_assignment.view.ClassicMusicLayoutFragment
import com.example.musicapi_assignment.view.TabLayoutFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityMainBinding
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.beginTransaction().replace(R.id.music_tab_layout, TabLayoutFragment()).commit()

        executeRetrofit(CLASSIC_TERM)
        setContentView(binding.root)

    }

    fun executeRetrofit(searchTerm : String){
        IMusicAPI.initRetrofit().getClassicTracks(searchTerm, MEDIA_TYPE, ENTITY_TYPE).enqueue(
            object : Callback<MusicResponse>{
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>,
                ) {
                    if(response.isSuccessful) {
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
            supportFragmentManager.beginTransaction().replace(R.id.music_items_layout,
                ClassicMusicLayoutFragment.newInstance(it)).commit()
        }
    }
}