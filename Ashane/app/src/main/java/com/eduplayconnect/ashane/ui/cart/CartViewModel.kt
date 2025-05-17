package com.eduplayconnect.ashane.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.ashane.data.model.SepetYemekModel
import com.eduplayconnect.ashane.data.repository.FoodRepository
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    
    private val repository = FoodRepository()
    
    private val _cartItems = MutableLiveData<Resource<List<SepetYemekModel>>>()
    val cartItems: LiveData<Resource<List<SepetYemekModel>>> = _cartItems
    
    private val _totalAmount = MutableLiveData<Int>()
    val totalAmount: LiveData<Int> = _totalAmount
    
    private val _removeItemStatus = MutableLiveData<Resource<String>>()
    val removeItemStatus: LiveData<Resource<String>> = _removeItemStatus
    
    private val _clearCartStatus = MutableLiveData<Resource<String>>()
    val clearCartStatus: LiveData<Resource<String>> = _clearCartStatus
    
    init {
        loadCartItems()
    }
    
    fun loadCartItems() {
        _cartItems.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.getCartFoods()
            _cartItems.value = result
            
            if (result is Resource.Success) {
                calculateTotalAmount(result.data)
            }
        }
    }
    
    private fun calculateTotalAmount(cartItems: List<SepetYemekModel>) {
        var total = 0
        cartItems.forEach { item ->
            total += item.getTotalPrice()
        }
        _totalAmount.value = total
    }
    
    fun removeFromCart(cartItem: SepetYemekModel) {
        _removeItemStatus.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.removeFromCart(cartItem.cartId)
            _removeItemStatus.value = result
            
            // Reload cart items after successful removal
            if (result is Resource.Success) {
                loadCartItems()
            }
        }
    }
    
    fun clearCart() {
        _clearCartStatus.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.clearCart()
            _clearCartStatus.value = result
            
            // Reload cart items after successful cart clearing
            if (result is Resource.Success) {
                loadCartItems()
            }
        }
    }
} 