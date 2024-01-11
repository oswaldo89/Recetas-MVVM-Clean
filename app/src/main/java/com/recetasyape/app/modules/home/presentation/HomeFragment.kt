package com.recetasyape.app.modules.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetasyape.app.modules.home.presentation.adapters.CategoryListAdapter
import com.recetasyape.app.databinding.FragmentHomeBinding
import com.recetasyape.app.modules.home.data.dto.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recetasAdapter: CategoryListAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeState()
        observeNavigation()

        viewModel.init()


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvRecetas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeState() {
        viewModel.stateLiveData.observe(requireActivity()) {
            when (it) {
                is HomeViewModel.State.ShowCategories -> showData(it.categories)
                else -> {}
            }
        }
    }

    private fun observeNavigation() {}

    private fun showData(categories: List<Category>) {
        binding.apply {
            recetasAdapter = CategoryListAdapter(categories)
            rvRecetas.adapter = recetasAdapter
        }
    }

}