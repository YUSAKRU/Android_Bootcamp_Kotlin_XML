package com.example.navigationcomponentkullanimi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponentkullanimi.databinding.FragmentDetayBinding

class DetayFragment : Fragment() {
    private  lateinit var  binding: FragmentDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetayBinding.inflate(inflater, container, false)

        val bundle: DetayFragmentArgs by navArgs()
        val gelenMesaj = bundle.mesaj
        val gelenSayi = bundle.sayi

        binding.textViewSonuc.text = "$gelenMesaj - $gelenSayi"

        val geriTusu = object : OnBackPressedCallback(enabled = true){ // true: geri donus aktif degil
            override fun handleOnBackPressed() {
                Log.e("Detay Sayfa", "Geri tusu calisti")
                isEnabled = false
            }
        }



        return binding.root
    }
}