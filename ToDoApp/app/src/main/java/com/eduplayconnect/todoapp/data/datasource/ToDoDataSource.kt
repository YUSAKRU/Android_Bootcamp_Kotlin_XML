package com.eduplayconnect.todoapp.data.datasource

import com.eduplayconnect.todoapp.data.entity.ToDo

interface ToDoDataSource {
    suspend fun getAllToDos(): List<ToDo>
    suspend fun searchToDos(searchQuery: String): List<ToDo>
    suspend fun insertToDo(name: String)
    suspend fun updateToDo(id: Int, name: String)
    suspend fun deleteToDo(id: Int)
} 