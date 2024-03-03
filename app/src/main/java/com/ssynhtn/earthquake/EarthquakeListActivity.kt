package com.ssynhtn.earthquake

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ssynhtn.earthquake.databinding.ActivityEarthquakeListBinding

class EarthquakeListActivity : ComponentActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}

