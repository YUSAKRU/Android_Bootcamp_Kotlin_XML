package com.example.odev4navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.odev4navigationapp.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        binding.buttonToPageA.setOnClickListener {
            val pageAFragment = PageAFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, pageAFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonToPageX.setOnClickListener {
            val pageXFragment = PageXFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, pageXFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}