package com.ssynhtn.earthquake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssynhtn.earthquake.databinding.ItemEarthquakeBinding
import com.ssynhtn.earthquake.model.ui.Earthquake
import com.ssynhtn.earthquake.util.TimeUtil

class EarthquakeAdapter : ListAdapter<Earthquake, CardViewHolder>(object : DiffUtil.ItemCallback<Earthquake>() {
    override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(ItemEarthquakeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CardViewHolder(private val binding: ItemEarthquakeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(earthquake: Earthquake) {
        binding.textMagnitude.text = earthquake.magnitude
        binding.textPlace.text = earthquake.place
        binding.textTime.text = TimeUtil.formatDate(earthquake.time)
    }
}