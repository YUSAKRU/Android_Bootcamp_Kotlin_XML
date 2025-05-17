package com.eduplayconnect.ashane.ui.checkout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.eduplayconnect.ashane.R
import com.eduplayconnect.ashane.databinding.FragmentCheckoutBinding
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckoutFragment : Fragment() {
    
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var successMessageTextView: TextView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupUI()
        setupListeners()
        observeViewModel()
        
        // Get arguments from bundle
        arguments?.let { args ->
            val totalAmount = args.getInt("totalAmount", 0)
            val itemCount = args.getInt("itemCount", 0)
            
            // Set the order summary data to the ViewModel
            viewModel.setOrderSummary(totalAmount, itemCount)
        }
    }
    
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
    }
    
    private fun setupUI() {
        lottieAnimationView = binding.lottieOrderSuccess
        successMessageTextView = requireView().findViewById(R.id.tvSuccessMessage)
    }
    
    private fun setupListeners() {
        binding.btnCompleteOrder.setOnClickListener {
            // Check if address and phone are filled
            if (binding.etAddress.text.isNullOrBlank()) {
                binding.etAddress.error = "Lütfen teslimat adresi girin"
                return@setOnClickListener
            }
            
            if (binding.etPhoneNumber.text.isNullOrBlank()) {
                binding.etPhoneNumber.error = "Lütfen telefon numarası girin"
                return@setOnClickListener
            }
            
            // Clear the cart when order is completed
            viewModel.clearCart()
            
            // Show success animation
            showOrderSuccessAnimation()
            
            // Show success message
            Toast.makeText(
                requireContext(),
                "Siparişiniz alındı! (Simülasyon)",
                Toast.LENGTH_SHORT
            ).show()
            
            // Navigate after animation finishes (approximately 3 seconds)
            lifecycleScope.launch {
                delay(3000) // Wait for 3 seconds for the animation to complete
                if (isAdded) { // Check if fragment is still attached
                    // Navigate back to home fragment and clear the back stack
                    findNavController().navigate(
                        R.id.homeFragment,
                        null,
                        androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.homeFragment, true)
                            .build()
                    )
                }
            }
        }
    }
    
    private fun showOrderSuccessAnimation() {
        // Hide form elements
        binding.tvDeliveryTitle.visibility = View.GONE
        binding.cardDeliveryInfo.visibility = View.GONE
        binding.tvPaymentTitle.visibility = View.GONE
        binding.cardPaymentInfo.visibility = View.GONE
        binding.tvOrderSummaryTitle.visibility = View.GONE
        binding.cardOrderSummary.visibility = View.GONE
        binding.btnCompleteOrder.visibility = View.GONE
        
        // Show and play animation
        lottieAnimationView.visibility = View.VISIBLE
        successMessageTextView.visibility = View.VISIBLE
        
        // Ensure we're using the correct animation file
        lottieAnimationView.setAnimation("new_success_animation.json")
        lottieAnimationView.playAnimation()
    }
    
    private fun observeViewModel() {
        viewModel.totalAmount.observe(viewLifecycleOwner) { totalAmount ->
            binding.tvTotalAmount.text = "₺$totalAmount"
        }
        
        viewModel.itemCount.observe(viewLifecycleOwner) { itemCount ->
            binding.tvItemCount.text = "$itemCount adet"
        }
        
        viewModel.clearCartStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Cart cleared successfully, no need to show any UI feedback
                    // Log the success for debugging purposes
                    Log.d("CheckoutFragment", "Cart cleared: ${resource.data}")
                }
                is Resource.Error -> {
                    // Show a message only if there was an error
                    Log.e("CheckoutFragment", "Error clearing cart: ${resource.message}")
                    // We don't show this to the user as it would interrupt the success flow
                }
                is Resource.Loading -> {
                    // Loading state, no UI feedback needed
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        lottieAnimationView.cancelAnimation() // Cancel animation when fragment is destroyed
        _binding = null
    }
} 