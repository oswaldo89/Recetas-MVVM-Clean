package com.recetasyape.app.modules.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetasyape.app.MainActivity.Companion.DATA_BUNDLE
import com.recetasyape.app.R
import com.recetasyape.app.databinding.FragmentDetailBinding
import com.recetasyape.app.modules.detail.presentation.adapters.IngredientsAdapter
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.map.presentation.MapFragment
import com.recetasyape.app.utils.OneTimeEventObserver
import com.recetasyape.app.utils.WhatsAppShareUtil
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
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setData()
        setupRecyclerView()
        setListeners()
        observeState()
        observeNavigation()

        return binding.root
    }

    private fun setData() {
        data = arguments?.parcelable(DATA_BUNDLE)
        data?.let {
            viewModel.onLoadRecipe(it)
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvIngredients.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvIngredients.setHasFixedSize(true)
            rvIngredients.isNestedScrollingEnabled = true
        }
    }

    private fun observeState() {
        viewModel.stateLiveData.observe(requireActivity()) {
            when (it) {
                is DetailViewModel.State.LoadRecipe -> updateUIWithRecipe(it.recipe)
                else -> {}
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner, OneTimeEventObserver {
            when (it) {
                is DetailViewModel.Navigation.GoToMap -> openMap(it.recipe)
                is DetailViewModel.Navigation.ImageViewer -> openImageViewer(it.url)
                is DetailViewModel.Navigation.ShareSocialNetworks -> shareRecipe(it.recipe)
            }
        })
    }

    private fun shareRecipe(recipe: Recipe) {
        WhatsAppShareUtil.shareRecipeOnWhatsApp(requireContext(), recipe)
    }

    private fun openImageViewer(url: String) {
        requireActivity().openImageInViewer(url)
    }

    private fun setListeners() {
        binding.apply {
            val fragmentManager = requireActivity().supportFragmentManager
            btnBack.setOnSafeClickListener { fragmentManager.popBackStack() }
            imageBanner.setOnSafeClickListener { viewModel.onImageClicked(data?.imageUrl.orEmpty()) }
            btnMap.setOnSafeClickListener { data?.let { viewModel.onLoadMap(it) } }
            share.setOnSafeClickListener { data?.let { viewModel.onShareClicked(it) } }
        }
    }

    private fun updateUIWithRecipe(recipe: Recipe) {
        binding.apply {
            imageBanner.loadUrl(requireContext(), recipe.imageUrl)
            title.text = recipe.title
            description.text = recipe.description
            ratingBar.rating = recipe.rating
            ingredients.text = recipe.ingredientsCount.toString()
            difficulty.text = recipe.difficultyLevel
            time.text = recipe.time.toString()
            fat.text = recipe.calories.toString()

            ingredientsAdapter = IngredientsAdapter(recipe.ingredients)
            rvIngredients.adapter = ingredientsAdapter
        }
    }

    private fun openMap(recipe: Recipe) {
        requireActivity().supportFragmentManager.hideAndAddFragment(R.id.nav_host_fragment, MapFragment(), dataObject = recipe)
    }

}