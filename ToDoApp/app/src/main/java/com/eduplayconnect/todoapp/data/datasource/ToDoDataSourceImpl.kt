package com.eduplayconnect.todoapp.data.datasource

import com.eduplayconnect.todoapp.data.entity.ToDo
import javax.inject.Inject

class ToDoDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoDataSource {
    
    override suspend fun getAllToDos(): List<ToDo> = toDoDao.getAllToDos()
    
    override suspend fun searchToDos(searchQuery: String): List<ToDo> = toDoDao.searchToDos(searchQuery)
    
    override suspend fun insertToDo(name: String) {
        val toDo = ToDo(0, name)
        toDoDao.insertToDo(toDo)
    }
    
    override suspend fun updateToDo(id: Int, name: String) {
        val toDo = ToDo(id, name)
        toDoDao.updateToDo(toDo)
    }
    
    override suspend fun deleteToDo(id: Int) {
        val toDo = ToDo(id, "")
        toDoDao.deleteToDo(toDo)
    }
} 