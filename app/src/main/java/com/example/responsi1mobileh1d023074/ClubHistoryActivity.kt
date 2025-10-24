package com.example.responsi1mobileh1d023074

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ClubHistoryActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "ClubHistoryActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            enableEdgeToEdge()
            setContentView(R.layout.activity_club_history)
            
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val backButton = findViewById<Button>(R.id.btn_back)
            backButton?.setOnClickListener {
                finish()
            }
            
            Log.d(TAG, "ClubHistoryActivity created successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate: ${e.message}", e)
            finish()
        }
    }
}
