package com.eduplayconnect.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eduplayconnect.todoapp.databinding.FragmentDetayBinding
import com.eduplayconnect.todoapp.ui.viewmodel.DetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: DetayViewModel by viewModels()
    private val args: DetayFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val toDo = args.toDo
        binding.editTextToDoName.setText(toDo.name)
        
        binding.buttonGuncelle.setOnClickListener {
            val toDoName = binding.editTextToDoName.text.toString()
            viewModel.update(toDo.id, toDoName)
            
            Navigation.findNavController(it).popBackStack()
        }
        
        binding.buttonSil.setOnClickListener {
            viewModel.delete(toDo.id)
            
            Navigation.findNavController(it).popBackStack()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 