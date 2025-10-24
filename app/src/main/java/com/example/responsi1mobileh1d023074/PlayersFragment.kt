package com.example.responsi1mobileh1d023074

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023074.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023074.databinding.FragmentPlayersBinding
import com.example.responsi1mobileh1d023074.model.Player
import com.example.responsi1mobileh1d023074.network.NetworkModule
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PlayersFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    
    private val apiService = NetworkModule.apiService
    private val apiToken = NetworkModule.getApiToken()
    private val teamId = 332
    
    private lateinit var playerAdapter: PlayerAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupToolbar()
        setupRecyclerView()
        setupPlayerDetailCard()
        loadPlayersData()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    
    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter { player ->
            showPlayerDetail(player)
        }
        
        binding.recyclerViewPlayers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playerAdapter
        }
    }
    
    private fun setupPlayerDetailCard() {
        binding.playerDetailCard.visibility = View.GONE

        binding.root.setOnClickListener {
            hidePlayerDetail()
        }
    }
    
    private fun showPlayerDetail(player: Player) {
        binding.playerDetailName.text = player.name
        binding.playerDetailDob.text = formatDate(player.dateOfBirth)
        binding.playerDetailNationality.text = player.nationality
        binding.playerDetailPosition.text = player.position
        
        binding.playerDetailCard.visibility = View.VISIBLE

        binding.playerDetailCard.alpha = 0f
        binding.playerDetailCard.animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }
    
    private fun hidePlayerDetail() {
        binding.playerDetailCard.animate()
            .alpha(0f)
            .setDuration(300)
            .withEndAction {
                binding.playerDetailCard.visibility = View.GONE
            }
            .start()
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
    
    private fun loadPlayersData() {
        binding.progressBar.visibility = View.VISIBLE
        
        lifecycleScope.launch {
            try {
                val response = apiService.getTeam(apiToken, teamId)
                if (response.isSuccessful) {
                    val team = response.body()
                    team?.squad?.let { players ->
                        playerAdapter.submitList(players)
                    }
                } else {
                    showError("Failed to load players data: ${response.code()}")
                }
            } catch (e: Exception) {
                showError("Error loading players data: ${e.message}")
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
