package com.example.responsi1mobileh1d023074

import android.os.Bundle
import android.widget.Toast
import android.widget.TextView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.responsi1mobileh1d023074.model.Coach
import com.example.responsi1mobileh1d023074.network.NetworkModule
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CoachActivity : AppCompatActivity() {
    private val apiService = NetworkModule.apiService
    private val apiToken = NetworkModule.getApiToken()

    private val teamId = 332
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)
        
        setupToolbar()
        loadCoachData()
    }
    
    private fun setupToolbar() {
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun loadCoachData() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = android.view.View.VISIBLE
        
        lifecycleScope.launch {
            try {
                val response = apiService.getTeam(apiToken, teamId)
                if (response.isSuccessful) {
                    val team = response.body()
                    team?.coach?.let { coach ->
                        displayCoachData(coach)
                    }
                } else {
                    showError("Failed to load coach data: ${response.code()}")
                }
            } catch (e: Exception) {
                showError("Error loading coach data: ${e.message}")
            } finally {
                progressBar.visibility = android.view.View.GONE
            }
        }
    }
    
    private fun displayCoachData(coach: Coach) {
        val coachName = findViewById<TextView>(R.id.coach_name)
        val coachDob = findViewById<TextView>(R.id.coach_dob)
        val coachNationality = findViewById<TextView>(R.id.coach_nationality)
        
        coachName.text = coach.name
        coachDob.text = formatDate(coach.dateOfBirth)
        coachNationality.text = coach.nationality
    }
    
    private fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date ?: Date())
        } catch (e: Exception) {
            dateString
        }
    }
    
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
