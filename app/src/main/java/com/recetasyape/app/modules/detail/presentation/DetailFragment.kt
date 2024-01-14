package com.recetasyape.app.modules.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetasyape.app.MainActivity.Companion.DATA_BUNDLE
import com.recetasyape.app.R
import com.recetasyape.app.databinding.FragmentDetailBinding
import com.recetasyape.app.modules.detail.presentation.adapters.IngredientsAdapter
import com.recetasyape.app.modules.home.data.dto.DataRecipe
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.home.presentation.adapters.CategoryListAdapter
import com.recetasyape.app.modules.map.presentation.MapFragment
import com.recetasyape.app.utils.extension_functions.hideAndAddFragment
import com.recetasyape.app.utils.extension_functions.loadUrl
import com.recetasyape.app.utils.extension_functions.openImageInViewer
import com.recetasyape.app.utils.extension_functions.parcelable
import com.recetasyape.app.utils.extension_functions.setOnSafeClickListener


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var data: Recipe? = null

    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setListeners()
        setData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvIngredients.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvIngredients.setHasFixedSize(true)
            rvIngredients.isNestedScrollingEnabled = true
        }
    }

    private fun setListeners() {
        binding.apply {
            val fragmentManager = requireActivity().supportFragmentManager
            btnBack.setOnSafeClickListener { fragmentManager.popBackStack() }
            btnMap.setOnSafeClickListener {
                requireActivity().supportFragmentManager.hideAndAddFragment(R.id.nav_host_fragment, MapFragment(), dataObject = data)
            }
            imageBanner.setOnSafeClickListener {
                requireActivity().openImageInViewer(data?.imageUrl.orEmpty())
            }
        }
    }

    private fun setData() {
        data = arguments?.parcelable<Recipe>(DATA_BUNDLE)?.apply {
            val item = this
            binding.apply {
                imageBanner.loadUrl(requireContext(), item.imageUrl)
                title.text = item.title
                description.text = item.description
                ratingBar.rating = item.rating
                ingredients.text = item.ingredientsCount.toString()
                difficulty.text = item.difficultyLevel
                time.text = item.time.toString()
                fat.text = item.calories.toString()

                ingredientsAdapter = IngredientsAdapter(item.ingredients)
                rvIngredients.adapter = ingredientsAdapter
            }
        }
    }
}