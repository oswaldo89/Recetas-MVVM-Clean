package com.recetasyape.app.modules.map.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.recetasyape.app.R
import com.recetasyape.app.databinding.FragmentMapBinding
import com.recetasyape.app.modules.home.data.dto.Recipe
import com.recetasyape.app.utils.extension_functions.parcelable

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private var data: Recipe? = null
    private var title: String = String()
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataMap()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setDataMap() {
        data = arguments?.parcelable<Recipe>("data")?.apply {
            val item = this
            this@MapFragment.title = item.title
            latitude = item.location.latitude
            longitude = item.location.longitude
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val marker = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(marker).title(title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }
}