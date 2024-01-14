package com.recetasyape.app.modules.detail.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recetasyape.app.databinding.ItemIngredientBinding

class IngredientsAdapter(private var items: List<String>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                val item = this
                binding.apply {
                    description.text = "${position + 1}.- $item"
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}