package com.ssynhtn.earthquake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.gms.maps.model.LatLng
import com.ssynhtn.earthquake.databinding.ActivityEarthquakeListBinding
import com.ssynhtn.earthquake.model.ui.Earthquake

class EarthquakeListActivity : AppCompatActivity(), OnItemClickListener<Earthquake> {

    private lateinit var binding: ActivityEarthquakeListBinding
    private val viewModel by viewModels<EarthquakeListViewModel>()
    private val adapter = EarthquakeAdapter().also { it.onItemClickListener = this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservers()

    }

    private fun initViews() {
        binding.recyclerView.apply {
            adapter = this@EarthquakeListActivity.adapter
            layoutManager = LinearLayoutManager(this@EarthquakeListActivity)
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initObservers() {
        viewModel.items.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.loading.observe(this) {
            it?.let {
                binding.swipeRefresh.isRefreshing = it
            }
        }
    }

    override fun onItemClick(item: Earthquake) {
        MapActivity.start(this, LatLng(item.coordinate.latitude, item.coordinate.longitude), item.place)
    }
}

