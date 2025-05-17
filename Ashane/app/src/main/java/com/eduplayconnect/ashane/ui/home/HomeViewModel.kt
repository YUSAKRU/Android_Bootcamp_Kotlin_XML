package com.eduplayconnect.ashane.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.ashane.data.model.YemekModel
import com.eduplayconnect.ashane.data.repository.FoodRepository
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    
    private val repository = FoodRepository()
    
    private val _foodList = MutableLiveData<Resource<List<YemekModel>>>()
    val foodList: LiveData<Resource<List<YemekModel>>> = _foodList
    
    private val _searchQuery = MutableLiveData<String>()
    
    private val _filteredFoodList = MutableLiveData<List<YemekModel>>()
    val filteredFoodList: LiveData<List<YemekModel>> = _filteredFoodList
    
    init {
        loadFoods()
    }
    
    fun loadFoods() {
        _foodList.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.getAllFoods()
            _foodList.value = result
            
            // Initialize filtered list with all foods
            if (result is Resource.Success) {
                _filteredFoodList.value = result.data
            }
        }
    }
    
    fun searchFoods(query: String) {
        _searchQuery.value = query
        
        val currentList = (_foodList.value as? Resource.Success)?.data ?: return
        
        if (query.isEmpty()) {
            _filteredFoodList.value = currentList
            return
        }
        
        _filteredFoodList.value = currentList.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
} 