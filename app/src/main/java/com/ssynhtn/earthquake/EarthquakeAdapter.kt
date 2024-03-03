package com.ssynhtn.earthquake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssynhtn.earthquake.databinding.ItemEarthquakeBinding
import com.ssynhtn.earthquake.model.ui.Earthquake
import com.ssynhtn.earthquake.util.TimeUtil

class EarthquakeAdapter :
    ListAdapter<Earthquake, CardViewHolder>(object : DiffUtil.ItemCallback<Earthquake>() {
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem == newItem
        }

    }) {

    var onItemClickListener: OnItemClickListener<Earthquake>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            ItemEarthquakeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        // delegates binding logic to view holder
        holder.bind(getItem(position))
    }
}

class CardViewHolder(
    private val binding: ItemEarthquakeBinding,
    private val onItemClickListener: OnItemClickListener<Earthquake>?
) :
    RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    fun bind(earthquake: Earthquake) {
        binding.textMagnitude.text = String.format("%.1f", earthquake.magnitude)
        val colorRes = if (earthquake.strong) {
            android.R.color.holo_red_dark
        } else {
            R.color.colorPrimary
        }
        binding.textMagnitude.setTextColor(context.getColor(colorRes))
        binding.textPlace.text = context.getString(R.string.location, earthquake.place)
        binding.textTime.text =
            context.getString(R.string.time, TimeUtil.formatDate(earthquake.time))

        binding.root.setOnClickListener {
            onItemClickListener?.onItemClick(earthquake)
        }
    }
}

interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}