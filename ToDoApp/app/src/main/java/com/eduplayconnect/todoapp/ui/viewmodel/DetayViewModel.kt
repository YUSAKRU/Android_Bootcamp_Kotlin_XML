package com.eduplayconnect.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {
    
    fun update(id: Int, name: String) {
        viewModelScope.launch {
            repository.updateToDo(id, name)
        }
    }
    
    fun delete(id: Int) {
        viewModelScope.launch {
            repository.deleteToDo(id)
        }
    }
} 