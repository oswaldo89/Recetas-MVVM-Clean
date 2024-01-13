package com.recetasyape.app.modules.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.recetasyape.app.R
import com.recetasyape.app.databinding.FragmentDetailBinding
import com.recetasyape.app.modules.home.data.dto.Recipe
import com.recetasyape.app.modules.map.presentation.MapFragment
import com.recetasyape.app.utils.extension_functions.hideAndAddFragment
import com.recetasyape.app.utils.extension_functions.loadUrl
import com.recetasyape.app.utils.extension_functions.parcelable
import com.recetasyape.app.utils.extension_functions.setOnSafeClickListener


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var data : Recipe? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setData()
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.apply {
            val fragmentManager = requireActivity().supportFragmentManager
            btnBack.setOnSafeClickListener { fragmentManager.popBackStack() }
            btnMap.setOnSafeClickListener {
                requireActivity().supportFragmentManager.hideAndAddFragment(R.id.nav_host_fragment, MapFragment(), dataObject = data)
            }
        }
    }

    private fun setData() {
        data = arguments?.parcelable<Recipe>("data")?.apply {
            val item = this
            binding.apply {
                imageBanner.loadUrl(requireContext(), item.imageUrl)
                title.text = item.title
                description.text = item.description
                ratingBar.rating = 3f;
            }
        }
    }
}