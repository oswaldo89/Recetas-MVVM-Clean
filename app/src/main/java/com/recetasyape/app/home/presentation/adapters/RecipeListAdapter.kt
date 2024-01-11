package com.recetasyape.app.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recetasyape.app.home.data.dto.Recipe
import com.recetasyape.app.databinding.ItemRecipeBinding
import com.recetasyape.app.utils.extension_functions.setOnSafeClickListener

class RecipeListAdapter(private var items: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.categoryName.text = this.categoryName
            }
            this.itemView.setOnSafeClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}