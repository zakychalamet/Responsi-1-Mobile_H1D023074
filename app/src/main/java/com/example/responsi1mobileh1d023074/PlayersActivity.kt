package com.example.responsi1mobileh1d023074

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class PlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, PlayersFragment())
            }
        }
    }
}


