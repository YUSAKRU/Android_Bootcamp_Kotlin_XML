package com.eduplayconnect.ashane.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduplayconnect.ashane.data.model.YemekModel
import com.eduplayconnect.ashane.databinding.ItemFoodBinding

class FoodAdapter(private val onFoodClick: (YemekModel) -> Unit) : 
    ListAdapter<YemekModel, FoodAdapter.FoodViewHolder>(FoodDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class FoodViewHolder(private val binding: ItemFoodBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onFoodClick(getItem(position))
                }
            }
        }
        
        fun bind(food: YemekModel) {
            binding.apply {
                tvFoodName.text = food.name
                tvFoodPrice.text = "₺${food.price}"
                
                Glide.with(ivFoodImage)
                    .load(food.getImageUrl())
                    .centerCrop()
                    .into(ivFoodImage)
            }
        }
    }
    
    class FoodDiffCallback : DiffUtil.ItemCallback<YemekModel>() {
        override fun areItemsTheSame(oldItem: YemekModel, newItem: YemekModel): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: YemekModel, newItem: YemekModel): Boolean {
            return oldItem == newItem
        }
    }
} 