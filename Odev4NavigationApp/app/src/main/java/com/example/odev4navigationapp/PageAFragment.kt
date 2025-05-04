package com.example.odev4navigationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.odev4navigationapp.databinding.FragmentPageABinding

class PageAFragment : Fragment() {

    private lateinit var binding: FragmentPageABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageABinding.inflate(inflater, container, false)

        binding.buttonToPageB .setOnClickListener {
            val pageBFragment = PageBFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, pageBFragment)
                .addToBackStack(null)
                .commit()
        }


        return binding.root
    }
}