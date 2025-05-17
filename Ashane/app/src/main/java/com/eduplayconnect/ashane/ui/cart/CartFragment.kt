package com.eduplayconnect.ashane.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduplayconnect.ashane.R
import com.eduplayconnect.ashane.databinding.FragmentCartBinding
import com.eduplayconnect.ashane.util.Resource

class CartFragment : Fragment() {
    
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupRecyclerView()
        observeViewModel()
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh cart items when fragment becomes active
        viewModel.loadCartItems()
    }
    
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }
    
    private fun setupRecyclerView() {
        cartAdapter = CartAdapter { cartItem ->
            // Handle remove item click
            viewModel.removeFromCart(cartItem)
        }
        
        binding.rvCartItems.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        
        binding.btnCheckout.setOnClickListener {
            // Get the total amount and item count
            val totalAmount = viewModel.totalAmount.value ?: 0
            val itemCount = cartAdapter.itemCount
            
            // Check if cart is not empty
            if (itemCount > 0) {
                // Navigate to checkout fragment with arguments
                findNavController().navigate(
                    R.id.action_cartFragment_to_checkoutFragment,
                    Bundle().apply {
                        putInt("totalAmount", totalAmount)
                        putInt("itemCount", itemCount)
                    }
                )
            } else {
                Toast.makeText(requireContext(), "Sepetiniz boş", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun observeViewModel() {
        viewModel.cartItems.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    
                    val cartItems = resource.data
                    if (cartItems.isEmpty()) {
                        showEmptyCart()
                    } else {
                        showCartItems()
                        cartAdapter.submitList(cartItems)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    if (resource.message.contains("empty") || resource.message.contains("No items")) {
                        showEmptyCart()
                    } else {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
        
        viewModel.totalAmount.observe(viewLifecycleOwner) { total ->
            binding.tvTotalAmount.text = "₺$total"
        }
        
        viewModel.removeItemStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Cart will be refreshed automatically
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Ürün silinirken hata oluştu: ${resource.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    // Loading state is already handled by cartItems observer
                }
            }
        }
    }
    
    private fun showEmptyCart() {
        binding.rvCartItems.visibility = View.GONE
        binding.tvEmptyCart.visibility = View.VISIBLE
        binding.cardTotalInfo.visibility = View.GONE
        binding.btnCheckout.visibility = View.GONE
    }
    
    private fun showCartItems() {
        binding.rvCartItems.visibility = View.VISIBLE
        binding.tvEmptyCart.visibility = View.GONE
        binding.cardTotalInfo.visibility = View.VISIBLE
        binding.btnCheckout.visibility = View.VISIBLE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 