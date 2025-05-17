package com.eduplayconnect.ashane.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduplayconnect.ashane.data.model.SepetYemekModel
import com.eduplayconnect.ashane.databinding.ItemCartBinding

class CartAdapter(private val onRemoveClick: (SepetYemekModel) -> Unit) : 
    ListAdapter<SepetYemekModel, CartAdapter.CartViewHolder>(CartDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class CartViewHolder(private val binding: ItemCartBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.btnRemoveFromCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onRemoveClick(getItem(position))
                }
            }
        }
        
        fun bind(cartItem: SepetYemekModel) {
            binding.apply {
                tvCartFoodName.text = cartItem.name
                tvCartFoodPrice.text = "₺${cartItem.price}"
                tvCartFoodQuantity.text = "Adet: ${cartItem.quantity}"
                
                // Use helper method to calculate item total
                tvCartItemTotal.text = "Toplam: ₺${cartItem.getTotalPrice()}"
                
                // Load image with Glide
                Glide.with(ivCartFoodImage)
                    .load(cartItem.getImageUrl())
                    .centerCrop()
                    .into(ivCartFoodImage)
            }
        }
    }
    
    class CartDiffCallback : DiffUtil.ItemCallback<SepetYemekModel>() {
        override fun areItemsTheSame(oldItem: SepetYemekModel, newItem: SepetYemekModel): Boolean {
            return oldItem.cartId == newItem.cartId
        }
        
        override fun areContentsTheSame(oldItem: SepetYemekModel, newItem: SepetYemekModel): Boolean {
            return oldItem == newItem
        }
    }
} 