package com.eduplayconnect.todoapp.di

import android.content.Context
import androidx.room.Room
import com.eduplayconnect.todoapp.data.datasource.ToDoDao
import com.eduplayconnect.todoapp.data.datasource.ToDoDataSource
import com.eduplayconnect.todoapp.data.datasource.ToDoDataSourceImpl
import com.eduplayconnect.todoapp.data.datasource.ToDoDatabase
import com.eduplayconnect.todoapp.data.repo.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo_database"
        ).createFromAsset("toDos.sqlite")
         .fallbackToDestructiveMigration()
         .build()
    }
    
    @Provides
    @Singleton
    fun provideToDoDao(database: ToDoDatabase): ToDoDao {
        return database.toDoDao()
    }
    
    @Provides
    @Singleton
    fun provideToDoDataSource(toDoDao: ToDoDao): ToDoDataSource {
        return ToDoDataSourceImpl(toDoDao)
    }
    
    @Provides
    @Singleton
    fun provideToDoRepository(dataSource: ToDoDataSource): ToDoRepository {
        return ToDoRepository(dataSource)
    }
} 