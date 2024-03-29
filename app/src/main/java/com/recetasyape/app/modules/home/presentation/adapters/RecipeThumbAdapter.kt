package com.recetasyape.app.modules.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recetasyape.app.databinding.ItemRecipeBinding
import com.recetasyape.app.modules.home.data.dto.DataRecipe
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.utils.extension_functions.loadUrl
import com.recetasyape.app.utils.extension_functions.setOnSafeClickListener

interface IRecipeEvent {
    fun onRecipeClick(recipe: Recipe)
}

class RecipeThumbAdapter(private var items: List<Recipe>, private var recipeEvent: IRecipeEvent) : RecyclerView.Adapter<RecipeThumbAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.thumbMovie.loadUrl(context = holder.itemView.context, url = this.imageUrl)
                binding.title.text = this.title

                binding.thumbMovie.setOnSafeClickListener {
                    recipeEvent.onRecipeClick(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}