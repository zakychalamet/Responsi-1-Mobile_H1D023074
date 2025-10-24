package com.example.responsi1mobileh1d023074

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "MainActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            setupMenuItems()
            
            Log.d(TAG, "MainActivity created successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate: ${e.message}", e)
        }
    }
    
    private fun setupMenuItems() {
        try {
            val clubHistoryCard = findViewById<com.google.android.material.card.MaterialCardView>(R.id.layout_club)
            val clubHistoryText = clubHistoryCard.findViewById<TextView>(R.id.tv_layout_club)
            val clubHistoryIcon = clubHistoryCard.findViewById<ImageView>(R.id.img_icon_club)
            clubHistoryText.text = getString(R.string.club_history)
            clubHistoryIcon.setImageResource(R.drawable.ball_icon)

            clubHistoryCard.setOnClickListener {
                try {
                    val intent = Intent(this, ClubHistoryActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting ClubHistoryActivity: ${e.message}", e)
                }
            }

            val coachCard = findViewById<com.google.android.material.card.MaterialCardView>(R.id.layout_coach)
            val coachText = coachCard.findViewById<TextView>(R.id.tv_layout_coach)
            val coachIcon = coachCard.findViewById<ImageView>(R.id.img_icon_coach)
            coachText.text = getString(R.string.head_coach)
            coachIcon.setImageResource(R.drawable.head_coach_icon)

            coachCard.setOnClickListener {
                try {
                    val intent = Intent(this, CoachActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting CoachActivity: ${e.message}", e)
                }
            }

            val teamCard = findViewById<com.google.android.material.card.MaterialCardView>(R.id.layout_team)
            val teamText = teamCard.findViewById<TextView>(R.id.tv_layout_team)
            val teamIcon = teamCard.findViewById<ImageView>(R.id.img_icon_team)
            teamText.text = getString(R.string.team_squad)
            teamIcon.setImageResource(R.drawable.team_squad_icon)

            teamCard.setOnClickListener {
                try {
                    val intent = Intent(this, PlayersActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting PlayersActivity: ${e.message}", e)
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in setupMenuItems: ${e.message}", e)
        }
    }
}