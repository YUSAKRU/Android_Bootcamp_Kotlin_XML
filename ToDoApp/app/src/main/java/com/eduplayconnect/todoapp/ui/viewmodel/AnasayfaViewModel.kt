package com.eduplayconnect.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduplayconnect.todoapp.data.entity.ToDo
import com.eduplayconnect.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {
    
    private val _toDoList = MutableLiveData<List<ToDo>>()
    val toDoList: LiveData<List<ToDo>> = _toDoList
    
    init {
        loadToDos()
    }
    
    fun loadToDos() {
        viewModelScope.launch {
            _toDoList.value = repository.getAllToDos()
        }
    }
    
    fun search(searchQuery: String) {
        viewModelScope.launch {
            _toDoList.value = repository.searchToDos(searchQuery)
        }
    }
} 