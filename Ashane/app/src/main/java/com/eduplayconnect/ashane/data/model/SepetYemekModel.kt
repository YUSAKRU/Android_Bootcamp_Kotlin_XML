package com.eduplayconnect.ashane.data.model

import com.google.gson.annotations.SerializedName

data class SepetYemekModel(
    @SerializedName("sepet_yemek_id")
    val cartId: String,
    
    @SerializedName("yemek_adi")
    val name: String,
    
    @SerializedName("yemek_resim_adi")
    val imageName: String,
    
    @SerializedName("yemek_fiyat")
    val price: String,
    
    @SerializedName("yemek_siparis_adet")
    val quantity: String,
    
    @SerializedName("kullanici_adi")
    val username: String
) {
    // Helper function to get the full image URL
    fun getImageUrl(): String {
        return "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
    }
    
    // Helper function to get price as Int
    fun getPriceAsInt(): Int {
        return price.toIntOrNull() ?: 0
    }
    
    // Helper function to get quantity as Int
    fun getQuantityAsInt(): Int {
        return quantity.toIntOrNull() ?: 0
    }
    
    // Helper function to calculate total price
    fun getTotalPrice(): Int {
        return getPriceAsInt() * getQuantityAsInt()
    }
} 