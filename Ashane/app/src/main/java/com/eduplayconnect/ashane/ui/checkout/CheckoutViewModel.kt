package com.eduplayconnect.ashane.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.ashane.data.repository.FoodRepository
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.launch

class CheckoutViewModel : ViewModel() {
    
    private val repository = FoodRepository()
    
    // LiveData for order total amount (received from CartFragment)
    private val _totalAmount = MutableLiveData<Int>()
    val totalAmount: LiveData<Int> = _totalAmount
    
    // LiveData for total item count (received from CartFragment)
    private val _itemCount = MutableLiveData<Int>()
    val itemCount: LiveData<Int> = _itemCount
    
    // LiveData for cart clearing status
    private val _clearCartStatus = MutableLiveData<Resource<String>>()
    val clearCartStatus: LiveData<Resource<String>> = _clearCartStatus
    
    // Function to set order summary data
    fun setOrderSummary(totalAmount: Int, itemCount: Int) {
        _totalAmount.value = totalAmount
        _itemCount.value = itemCount
    }
    
    // Function to clear the cart when order is completed
    fun clearCart() {
        _clearCartStatus.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.clearCart()
            _clearCartStatus.value = result
        }
    }
} 