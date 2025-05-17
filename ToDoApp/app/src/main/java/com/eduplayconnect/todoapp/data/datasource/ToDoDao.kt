package com.eduplayconnect.todoapp.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eduplayconnect.todoapp.data.entity.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM toDos")
    suspend fun getAllToDos(): List<ToDo>
    
    @Query("SELECT * FROM toDos WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchToDos(searchQuery: String): List<ToDo>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDo)
    
    @Update
    suspend fun updateToDo(toDo: ToDo)
    
    @Delete
    suspend fun deleteToDo(toDo: ToDo)
    
    @Query("SELECT * FROM toDos WHERE id = :toDoId")
    suspend fun getToDoById(toDoId: Int): ToDo?
} 