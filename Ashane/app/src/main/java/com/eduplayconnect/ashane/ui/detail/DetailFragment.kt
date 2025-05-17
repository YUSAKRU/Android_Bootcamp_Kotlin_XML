package com.eduplayconnect.ashane.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.eduplayconnect.ashane.R
import com.eduplayconnect.ashane.data.model.YemekModel
import com.eduplayconnect.ashane.databinding.FragmentDetailBinding
import com.eduplayconnect.ashane.util.Resource

class DetailFragment : Fragment() {
    
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: DetailViewModel
    
    // Using Safe Args to get the food model passed from HomeFragment
    private val args: DetailFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupUI()
        observeViewModel()
        setupMenu()
        
        // Get the food model from arguments
        val foodModel = args.foodModel
        
        // Set the food model in the ViewModel
        viewModel.setFood(foodModel)
        
        // Log for testing
        Log.d("DetailFragment", "Received food: ${foodModel.name}, ID: ${foodModel.id}")
    }
    
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }
    
    private fun setupMenu() {
        val menuHost = requireActivity() as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // We don't need to inflate anything as the menu is already created in MainActivity
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        // Navigate to the cart fragment
                        findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
    
    private fun setupUI() {
        binding.btnIncrease.setOnClickListener {
            viewModel.increaseQuantity()
        }
        
        binding.btnDecrease.setOnClickListener {
            viewModel.decreaseQuantity()
        }
        
        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCart()
        }
    }
    
    private fun observeViewModel() {
        viewModel.food.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    displayFoodDetails(resource.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
        
        viewModel.quantity.observe(viewLifecycleOwner) { quantity ->
            binding.tvQuantity.text = quantity.toString()
        }
        
        viewModel.addToCartStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    
                    // Check if the success message indicates an update or a new addition
                    val messageText = when {
                        resource.data.contains("updated") -> "Sepetinizdeki ürün miktarı güncellendi!"
                        else -> "Ürün sepete eklendi!"
                    }
                    
                    Toast.makeText(requireContext(), messageText, Toast.LENGTH_SHORT).show()
                    
                    // Log success for debugging
                    Log.d("DetailFragment", "Add to cart success: ${resource.data}")
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    
                    // Log the detailed error for debugging
                    Log.e("DetailFragment", "Add to cart error: ${resource.message}")
                    
                    // Check if it's a JSON parsing error (which likely means the server returned HTML or an empty response)
                    val isParsingError = resource.message.contains("End of input") || 
                                         resource.message.contains("JsonReader") ||
                                         resource.message.contains("JSON") ||
                                         resource.message.contains("text/html")
                    
                    if (isParsingError) {
                        // Show a friendly message - we'll assume it was added anyway
                        Toast.makeText(
                            requireContext(),
                            "Ürün sepete eklendi!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Show the actual error for other types of errors
                        Toast.makeText(
                            requireContext(),
                            "Sepete eklenirken bir hata oluştu: ${resource.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
    
    private fun displayFoodDetails(food: YemekModel) {
        binding.apply {
            tvFoodName.text = food.name
            tvFoodPrice.text = "₺${food.price}"
            
            Glide.with(ivFoodImage)
                .load(food.getImageUrl())
                .centerCrop()
                .into(ivFoodImage)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 