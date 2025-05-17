package com.eduplayconnect.ashane.data.repository

import android.util.Log
import com.eduplayconnect.ashane.data.model.CRUDResponse
import com.eduplayconnect.ashane.data.model.SepetYemekModel
import com.eduplayconnect.ashane.data.model.YemekModel
import com.eduplayconnect.ashane.data.remote.RetrofitClient
import com.eduplayconnect.ashane.util.NetworkUtils
import com.eduplayconnect.ashane.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodRepository {
    private val TAG = "FoodRepository"
    private val apiService = RetrofitClient.foodApiService
    
    // Default username for the app
    companion object {
        const val DEFAULT_USERNAME = "AshaneTestKullanicisi"
    }
    
    suspend fun getAllFoods(): Resource<List<YemekModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAllFoods()
                if (response.isSuccessful) {
                    val foodResponse = response.body()
                    if (foodResponse != null && foodResponse.success == 1) {
                        Resource.Success(foodResponse.foods)
                    } else {
                        Resource.Error("No foods found")
                    }
                } else {
                    Resource.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
    
    suspend fun addFoodToCart(food: YemekModel, quantity: Int): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Adding food to cart: ${food.name}, Quantity: $quantity")
                
                val response = apiService.addFoodToCart(
                    foodName = food.name,
                    foodImageName = food.imageName,
                    foodPrice = food.getPriceAsInt(),
                    quantity = quantity,
                    username = DEFAULT_USERNAME
                )
                
                Log.d(TAG, "API Response Code: ${response.code()}")
                
                if (response.isSuccessful) {
                    val crudResponse = response.body()
                    Log.d(TAG, "Response body: $crudResponse")
                    
                    if (crudResponse != null && crudResponse.success == 1) {
                        Resource.Success("Food added to cart successfully")
                    } else {
                        Resource.Error("Failed to add food to cart: Server returned ${response.code()}")
                    }
                } else {
                    Log.e(TAG, "Error response: ${response.errorBody()?.string()}")
                    Resource.Error("Error: HTTP ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception adding food to cart", e)
                Resource.Error(NetworkUtils.handleNetworkException(e))
            }
        }
    }
    
    suspend fun getCartFoods(): Resource<List<SepetYemekModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCartFoods(DEFAULT_USERNAME)
                Log.d(TAG, "Cart API Response Code: ${response.code()}")
                
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, "Cart API Response Body: $responseBody")
                    
                    val contentType = response.headers()["Content-Type"]
                    Log.d(TAG, "Content-Type: $contentType")
                    
                    // Check for empty or invalid response
                    if (responseBody == null) {
                        Log.d(TAG, "Empty cart response")
                        return@withContext Resource.Success(emptyList())
                    }
                    
                    if (contentType?.contains("text/html") == true && responseBody.toString().trim().isEmpty()) {
                        Log.d(TAG, "HTML response instead of JSON with empty content")
                        return@withContext Resource.Success(emptyList())
                    }
                    
                    if (responseBody != null && responseBody.success == 1) {
                        return@withContext Resource.Success(responseBody.cartFoods)
                    } else {
                        // Server returned success=0 or another value
                        Log.d(TAG, "Cart API returned success=${responseBody?.success}")
                        return@withContext Resource.Success(emptyList()) // Treat as empty cart
                    }
                } else {
                    // Non-200 response, check specific errors
                    if (response.code() == 404) {
                        Log.d(TAG, "Cart API returned 404, treating as empty cart")
                        return@withContext Resource.Success(emptyList())
                    } else {
                        Log.e(TAG, "Cart API error: ${response.code()} ${response.message()}")
                        return@withContext Resource.Error("Error: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception in getCartFoods", e)
                
                // Special handling for common JSON parsing errors that occur with empty responses
                if (e is com.google.gson.JsonSyntaxException || 
                    e.message?.contains("End of input") == true ||
                    e.message?.contains("Unterminated object") == true ||
                    e.message?.contains("JsonReader") == true) {
                    
                    Log.d(TAG, "JSON parsing exception - treating as empty cart")
                    return@withContext Resource.Success(emptyList())
                } else {
                    return@withContext Resource.Error(NetworkUtils.handleNetworkException(e))
                }
            }
        }
    }
    
    suspend fun removeFromCart(cartFoodId: String): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.removeFromCart(
                    cartFoodId = cartFoodId.toInt(),
                    username = DEFAULT_USERNAME
                )
                
                if (response.isSuccessful) {
                    val crudResponse = response.body()
                    if (crudResponse != null && crudResponse.success == 1) {
                        Resource.Success("Food removed from cart successfully")
                    } else {
                        Resource.Error("Failed to remove food from cart")
                    }
                } else {
                    Resource.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
    
    /**
     * Adds a food item to the cart or updates its quantity if it already exists
     * 
     * 1. First, checks if the item is already in the cart
     * 2. If it exists, removes the existing item and adds a new one with the updated quantity
     * 3. If it doesn't exist, simply adds the new item
     * 
     * @param food The food item to add to the cart
     * @param quantity The quantity to add
     * @return Resource<String> Success or error message
     */
    suspend fun addOrUpdateItemInCart(food: YemekModel, quantity: Int): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Adding/Updating cart item: ${food.name}, Quantity: $quantity")
                
                // First get the current cart contents
                val cartResult = getCartFoods()
                
                // We now handle JSON parsing errors in getCartFoods and return empty list
                // So we only need to check for other error types here
                if (cartResult is Resource.Error) {
                    Log.e(TAG, "Error getting cart contents: ${cartResult.message}")
                    // If we are getting API errors but not JSON parsing errors,
                    // Try to add the item directly anyway as a fallback
                    Log.d(TAG, "Attempting to add item directly despite cart fetch error")
                    return@withContext addFoodToCart(food, quantity)
                }
                
                val cartItems = (cartResult as Resource.Success).data
                
                // Look for the same item in the cart (matching by name)
                val existingItem = cartItems.find { it.name == food.name }
                
                if (existingItem != null) {
                    // Item already exists in cart, need to update quantity
                    Log.d(TAG, "Found existing item in cart: ${existingItem.name}, ID: ${existingItem.cartId}")
                    
                    // Calculate new quantity (existing + new)
                    val newQuantity = existingItem.getQuantityAsInt() + quantity
                    
                    // Remove the existing item
                    val removeResult = removeFromCart(existingItem.cartId)
                    if (removeResult is Resource.Error) {
                        Log.e(TAG, "Error removing existing item: ${removeResult.message}")
                        // If we can't remove, try to add directly anyway
                        Log.d(TAG, "Attempting to add item directly despite remove error")
                        return@withContext addFoodToCart(food, quantity)
                    }
                    
                    try {
                        // Add with new quantity
                        Log.d(TAG, "Re-adding item with new quantity: $newQuantity")
                        
                        val addResult = apiService.addFoodToCart(
                            foodName = food.name,
                            foodImageName = food.imageName,
                            foodPrice = food.getPriceAsInt(),
                            quantity = newQuantity,
                            username = DEFAULT_USERNAME
                        )
                        
                        Log.d(TAG, "API Response Code for add: ${addResult.code()}")
                        
                        return@withContext if (addResult.isSuccessful) {
                            val crudResponse = addResult.body()
                            Log.d(TAG, "Response body for add: $crudResponse")
                            
                            if (crudResponse != null && crudResponse.success == 1) {
                                Resource.Success("Cart updated successfully")
                            } else {
                                Resource.Error("Failed to update cart: Server returned ${addResult.code()}")
                            }
                        } else {
                            Log.e(TAG, "Error response for add: ${addResult.errorBody()?.string()}")
                            Resource.Error("Error: HTTP ${addResult.code()} - ${addResult.message()}")
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Exception re-adding item", e)
                        return@withContext Resource.Error(NetworkUtils.handleNetworkException(e))
                    }
                } else {
                    // Item doesn't exist, just add it normally
                    Log.d(TAG, "No existing item found, adding as new")
                    return@withContext addFoodToCart(food, quantity)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception in addOrUpdateItemInCart", e)
                
                // If we encounter a JSON parsing error, try to add directly
                if (e is com.google.gson.JsonSyntaxException || 
                    e.message?.contains("End of input") == true ||
                    e.message?.contains("Unterminated object") == true ||
                    e.message?.contains("JsonReader") == true) {
                    
                    Log.d(TAG, "JSON parsing exception - attempting direct add")
                    return@withContext addFoodToCart(food, quantity)
                }
                
                return@withContext Resource.Error(NetworkUtils.handleNetworkException(e))
            }
        }
    }
    
    suspend fun clearCart(): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                // First get all the items in the cart
                val cartResult = getCartFoods()
                
                if (cartResult is Resource.Error) {
                    return@withContext Resource.Error("Failed to get cart items: ${cartResult.message}")
                }
                
                val cartItems = (cartResult as Resource.Success).data
                
                if (cartItems.isEmpty()) {
                    return@withContext Resource.Success("Cart is already empty")
                }
                
                // Remove each item from the cart
                var successCount = 0
                var failCount = 0
                
                for (item in cartItems) {
                    when (val result = removeFromCart(item.cartId)) {
                        is Resource.Success -> successCount++
                        is Resource.Error -> failCount++
                        is Resource.Loading -> { /* Loading durumu işlem gerektirmez */ }
                    }
                }
                
                // Return appropriate message
                if (failCount > 0) {
                    if (successCount > 0) {
                        Resource.Success("Partially cleared cart: $successCount items removed, $failCount failed")
                    } else {
                        Resource.Error("Failed to clear cart: all $failCount items failed to remove")
                    }
                } else {
                    Resource.Success("Cart cleared successfully: $successCount items removed")
                }
            } catch (e: Exception) {
                Resource.Error("Error clearing cart: ${e.localizedMessage}")
            }
        }
    }
} 