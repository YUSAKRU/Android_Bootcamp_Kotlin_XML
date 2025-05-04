package com.example.odev4navigationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.odev4navigationapp.databinding.FragmentPageXBinding

class PageXFragment : Fragment() {

    private lateinit var binding: FragmentPageXBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageXBinding.inflate(inflater, container, false)

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