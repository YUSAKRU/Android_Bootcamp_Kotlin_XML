package com.eduplayconnect.ashane.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.ashane.data.model.YemekModel
import com.eduplayconnect.ashane.data.repository.FoodRepository
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    
    private val TAG = "DetailViewModel"
    private val repository = FoodRepository()
    
    private val _food = MutableLiveData<Resource<YemekModel>>()
    val food: LiveData<Resource<YemekModel>> = _food
    
    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity
    
    private val _addToCartStatus = MutableLiveData<Resource<String>>()
    val addToCartStatus: LiveData<Resource<String>> = _addToCartStatus
    
    fun loadFoodDetails(foodId: String) {
        // Currently we don't have a specific API endpoint to get a single food item
        // So for now, we'll just store the food data passed from HomeFragment
        // In a real app, you would make an API call here to get detailed information
    }
    
    fun setFood(food: YemekModel) {
        _food.value = Resource.Success(food)
    }
    
    fun increaseQuantity() {
        val currentQuantity = _quantity.value ?: 1
        if (currentQuantity < 10) { // Setting an upper limit
            _quantity.value = currentQuantity + 1
        }
    }
    
    fun decreaseQuantity() {
        val currentQuantity = _quantity.value ?: 1
        if (currentQuantity > 1) { // Ensuring quantity doesn't go below 1
            _quantity.value = currentQuantity - 1
        }
    }
    
    fun addToCart() {
        val food = (_food.value as? Resource.Success)?.data ?: return
        val quantity = _quantity.value ?: 1
        
        _addToCartStatus.value = Resource.Loading
        
        viewModelScope.launch {
            try {
                // Use the repository method directly - it already has error handling
                val result = repository.addOrUpdateItemInCart(food, quantity)
                _addToCartStatus.value = result
            } catch (e: Exception) {
                // Handle any uncaught exceptions
                Log.e(TAG, "Uncaught exception in addToCart", e)
                _addToCartStatus.value = Resource.Error("Beklenmeyen bir hata oluştu: ${e.message}")
            }
        }
    }
} 