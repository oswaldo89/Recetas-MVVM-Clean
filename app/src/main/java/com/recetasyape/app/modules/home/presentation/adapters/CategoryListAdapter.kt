package com.recetasyape.app.modules.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recetasyape.app.databinding.ItemCategoryBinding
import com.recetasyape.app.modules.home.data.dto.Category
import com.recetasyape.app.utils.extension_functions.setOnSafeClickListener

class CategoryListAdapter(private var items: List<Category>) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {

                val item = this
                binding.apply {
                    val recipeThumbAdapter = RecipeThumbAdapter(item.recipes)

                    categoryName.text = item.categoryName
                    rvRecipe.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                    rvRecipe.adapter = recipeThumbAdapter
                }

            }
            this.itemView.setOnSafeClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}