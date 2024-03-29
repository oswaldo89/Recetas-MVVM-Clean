package com.recetasyape.app.modules.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetasyape.app.R
import com.recetasyape.app.databinding.FragmentHomeBinding
import com.recetasyape.app.modules.detail.presentation.DetailFragment
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.home.presentation.adapters.CategoryListAdapter
import com.recetasyape.app.modules.home.presentation.adapters.ICategoryEvent
import com.recetasyape.app.utils.OneTimeEventObserver
import com.recetasyape.app.utils.extension_functions.hideAndAddFragment
import com.recetasyape.app.utils.extension_functions.setVisibleOrGone
import dagger.hilt.android.AndroidEntryPoint
import xyz.quaver.floatingsearchview.FloatingSearchView
import xyz.quaver.floatingsearchview.suggestions.model.SearchSuggestion


@AndroidEntryPoint
class HomeFragment : Fragment(), ICategoryEvent {

    private var dataTest: List<Recipe>? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recetasAdapter: CategoryListAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupSearchListener()
        observeState()
        observeNavigation()

        viewModel.init()
        return binding.root
    }

    private fun setupSearchListener() {
        binding.apply {
            etSearch.onQueryChangeListener = { _, newQuery -> viewModel.onSearchTyped(newQuery) }
            etSearch.onSearchListener = object : FloatingSearchView.OnSearchListener {
                override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                    val recipe: Recipe = searchSuggestion as Recipe
                    viewModel.onRecipeClicked(recipe)
                    etSearch.clearSearchFocus()
                    etSearch.setSearchText("")
                }

                override fun onSearchAction(currentQuery: String?) {}
            }
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeState() {
        viewModel.stateLiveData.observe(requireActivity()) {
            when (it) {
                is HomeViewModel.State.ShowCategories -> showData(it.categories)
                is HomeViewModel.State.Loading -> showLoading(it.loading)
                is HomeViewModel.State.SwapSuggestions -> swapSearchView(it.recipes)
                else -> {}
            }
        }
    }

    private fun swapSearchView(recipes: List<Recipe>) {
        binding.etSearch.swapSuggestions(recipes)
    }

    private fun showLoading(loading: Boolean) {
        binding.let {
            it.progressBar.setVisibleOrGone(loading)
            it.rvCategories.setVisibleOrGone(!loading)
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner, OneTimeEventObserver {
            when (it) {
                is HomeViewModel.Navigation.GoToDetail -> {
                    requireActivity().supportFragmentManager.hideAndAddFragment(R.id.nav_host_fragment, DetailFragment(), dataObject = it.recipe)
                }
            }
        })
    }

    private fun showData(categories: List<Category>) {
        binding.apply {
            recetasAdapter = CategoryListAdapter(categories, this@HomeFragment)
            rvCategories.adapter = recetasAdapter
            dataTest = categories[0].recipes
            etSearch.swapSuggestions(categories[0].recipes)
        }
    }

    override fun onRecipeClicked(recipe: Recipe) {
        //throw RuntimeException("Test Crash with slack") // Force a crash
        viewModel.onRecipeClicked(recipe)
    }

}