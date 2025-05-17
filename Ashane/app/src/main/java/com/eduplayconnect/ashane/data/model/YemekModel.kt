package com.eduplayconnect.ashane.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YemekModel(
    @SerializedName("yemek_id")
    val id: String,
    
    @SerializedName("yemek_adi")
    val name: String,
    
    @SerializedName("yemek_resim_adi")
    val imageName: String,
    
    @SerializedName("yemek_fiyat")
    val price: String
) : Parcelable {
    // Helper function to get the full image URL
    fun getImageUrl(): String {
        return "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
    }
    
    // Helper function to get price as Int
    fun getPriceAsInt(): Int {
        return price.toIntOrNull() ?: 0
    }
} 