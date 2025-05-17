package com.eduplayconnect.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eduplayconnect.todoapp.data.entity.ToDo
import com.eduplayconnect.todoapp.databinding.CardTodoBinding
import com.eduplayconnect.todoapp.ui.fragment.AnasayfaFragmentDirections

class ToDoAdapter(
    private val context: Context,
    private var toDoList: List<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    
    inner class ToDoViewHolder(val binding: CardTodoBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = CardTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }
    
    override fun getItemCount(): Int = toDoList.size
    
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = toDoList[position]
        val binding = holder.binding
        
        binding.toDoNesnesi = toDo
        
        binding.cardViewToDo.setOnClickListener { view ->
            val action = AnasayfaFragmentDirections.actionAnasayfaFragmentToDetayFragment(toDo)
            Navigation.findNavController(view).navigate(action)
        }
    }
    
    fun updateData(newToDoList: List<ToDo>) {
        toDoList = newToDoList
        notifyDataSetChanged()
    }
} 