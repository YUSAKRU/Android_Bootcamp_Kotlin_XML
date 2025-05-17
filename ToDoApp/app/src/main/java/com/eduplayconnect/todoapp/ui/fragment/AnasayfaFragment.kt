package com.eduplayconnect.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduplayconnect.todoapp.databinding.FragmentAnasayfaBinding
import com.eduplayconnect.todoapp.ui.adapter.ToDoAdapter
import com.eduplayconnect.todoapp.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: AnasayfaViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        binding.fab.setOnClickListener {
            val action = AnasayfaFragmentDirections.actionAnasayfaFragmentToKayitFragment()
            Navigation.findNavController(it).navigate(action)
        }
        
        viewModel.toDoList.observe(viewLifecycleOwner) { toDos ->
            val adapter = ToDoAdapter(requireContext(), toDos)
            binding.recyclerView.adapter = adapter
        }
        
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.search(it) }
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(it) }
                return true
            }
        })
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.loadToDos()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 