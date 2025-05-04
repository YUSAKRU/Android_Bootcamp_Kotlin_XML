package com.example.odev4navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.odev4navigationapp.databinding.FragmentPageYBinding

class PageYFragment : Fragment() {
    private lateinit var binding: FragmentPageYBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageYBinding.inflate(inflater, container, false)

        binding.buttonHomePage.setOnClickListener {
            val mainPageFragment = MainPageFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, mainPageFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root

    }
}