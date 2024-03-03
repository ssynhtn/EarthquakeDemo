package com.ssynhtn.earthquake

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.BundleCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ssynhtn.earthquake.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {
        val location = intent.extras?.let {
            BundleCompat.getParcelable(it, EXTRA_LOCATION, LatLng::class.java)
        } ?: LatLng(30.0, 100.0)
        val title = intent.getStringExtra(EXTRA_TITLE)
        map.addMarker(MarkerOptions()
            .position(location)
            .title(title)
        )?.showInfoWindow()

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, map.cameraPosition.zoom))

    }

    companion object {
        private const val EXTRA_LOCATION = "EXTRA_LOCATION"
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        fun start(context: Context, position: LatLng, title: String) {
            val intent = Intent(context, MapActivity::class.java)
                .putExtra(EXTRA_LOCATION, position)
                .putExtra(EXTRA_TITLE, title)
            context.startActivity(intent)
        }
    }
}