package com.ssynhtn.earthquake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ssynhtn.earthquake.databinding.ActivityEarthquakeListBinding

class EarthquakeListActivity : ComponentActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding
    private val viewModel by viewModels<EarthquakeListViewModel>()
    private val adapter = EarthquakeAdapter()

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
}

