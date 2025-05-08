package com.example.kisileruygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.data.entity.Kisiler
import com.example.kisileruygulamasi.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasi.ui.adapter.KisilerAdapter

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kisiKayitGecis)
        }

        val kisilerListesi = ArrayList<Kisiler>()
        val k1 = Kisiler(1, "Ali", "123456789")
        val k2 = Kisiler(2, "Ayşe", "987654321")
        val k3 = Kisiler(3, "Mehmet", "456789123")
        val k4 = Kisiler(4, "Fatma", "321654987")
        val k5 = Kisiler(5, "Ahmet", "654321789")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
        kisilerListesi.add(k3)
        kisilerListesi.add(k4)
        kisilerListesi.add(k5)

        val kisilerAdapter = KisilerAdapter(requireContext(), kisilerListesi)
        binding.kisilerRv.adapter = kisilerAdapter

        binding.kisilerRv.layoutManager = LinearLayoutManager(requireContext())
        //binding.kisilerRv.layoutManager = StaggeredGridLayoutManager (1, StaggeredGridLayoutManager.HORIZONTAL)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                ara(newText)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                ara(query)
                return true
            }
        })

        return binding.root
    }

    fun ara(aramaKelimesi: String?) {
        Log.e("Arama", aramaKelimesi.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.e("AnasayfaFragment", "onResume")
    }
}
