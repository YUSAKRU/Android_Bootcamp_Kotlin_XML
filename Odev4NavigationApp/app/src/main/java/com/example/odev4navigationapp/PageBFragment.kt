package com.example.odev4navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.odev4navigationapp.databinding.FragmentPageABinding
import com.example.odev4navigationapp.databinding.FragmentPageBBinding

class PageBFragment : Fragment() {

    private lateinit var binding: FragmentPageBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPageBBinding.inflate(inflater, container, false)

        binding.buttonPageY.setOnClickListener {
            val pageYFragment = PageYFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, pageYFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}