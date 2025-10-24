package com.example.responsi1mobileh1d023074.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023074.R
import com.example.responsi1mobileh1d023074.databinding.ItemPlayerBinding
import com.example.responsi1mobileh1d023074.model.Player
import java.text.SimpleDateFormat
import java.util.*

class PlayerAdapter(
    private val onPlayerClick: (Player) -> Unit
) : ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(PlayerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlayerViewHolder(
        private val binding: ItemPlayerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.playerName.text = player.name
            binding.playerPosition.text = player.position
            binding.playerNationality.text = player.nationality
            
            // Set card background color based on player position
            val positionColor = getPositionColor(player.position)
            binding.root.setCardBackgroundColor(positionColor)
            
            binding.root.setOnClickListener {
                onPlayerClick(player)
            }
        }
        
        private fun getPositionColor(position: String): Int {
            val positionLower = position.lowercase()
            return when {
                positionLower.contains("goalkeeper") || positionLower == "gk" -> 
                    ContextCompat.getColor(binding.root.context, R.color.position_goalkeeper)

                positionLower.contains("back") || positionLower.contains("defender") || 
                positionLower == "def" || positionLower == "cb" || positionLower == "lb" || positionLower == "rb" -> 
                    ContextCompat.getColor(binding.root.context, R.color.position_defender)

                positionLower.contains("midfield") || positionLower.contains("midfielder") || 
                positionLower == "mid" || positionLower == "cm" || positionLower == "lm" || 
                positionLower == "rm" || positionLower == "cdm" || positionLower == "cam" -> 
                    ContextCompat.getColor(binding.root.context, R.color.position_midfielder)

                positionLower.contains("winger") || positionLower.contains("forward") || 
                positionLower.contains("attacker") || positionLower.contains("striker") || 
                positionLower.contains("wing") || positionLower == "st" || positionLower == "lw" || 
                positionLower == "rw" || positionLower == "cf" -> 
                    ContextCompat.getColor(binding.root.context, R.color.position_forward)

                else -> ContextCompat.getColor(binding.root.context, R.color.position_forward)
            }
        }
    }

    class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }
}
