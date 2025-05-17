package com.eduplayconnect.todoapp.data.repo

import com.eduplayconnect.todoapp.data.datasource.ToDoDataSource
import com.eduplayconnect.todoapp.data.entity.ToDo
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val dataSource: ToDoDataSource
) {
    suspend fun getAllToDos(): List<ToDo> = dataSource.getAllToDos()
    
    suspend fun searchToDos(searchQuery: String): List<ToDo> = dataSource.searchToDos(searchQuery)
    
    suspend fun insertToDo(name: String) = dataSource.insertToDo(name)
    
    suspend fun updateToDo(id: Int, name: String) = dataSource.updateToDo(id, name)
    
    suspend fun deleteToDo(id: Int) = dataSource.deleteToDo(id)
} 