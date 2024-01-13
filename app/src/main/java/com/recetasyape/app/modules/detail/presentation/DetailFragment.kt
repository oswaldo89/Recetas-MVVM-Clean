package com.recetasyape.app.modules.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.recetasyape.app.databinding.FragmentDetailBinding
import com.recetasyape.app.modules.home.data.dto.Recipe
import com.recetasyape.app.utils.extension_functions.loadUrl
import com.recetasyape.app.utils.extension_functions.parcelable


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setData()

        binding.apply {
            ratingBar.rating = 3f;
        }

        return binding.root
    }


    private fun setData() {
        arguments?.parcelable<Recipe>("data")?.apply {
            val item = this
            binding.apply {
                imageBanner.loadUrl(requireContext(), item.imageUrl)
                title.text = item.title
                description.text = item.description
            }
        }
    }
}