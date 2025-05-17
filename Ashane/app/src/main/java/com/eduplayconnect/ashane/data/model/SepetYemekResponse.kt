package com.eduplayconnect.ashane.data.model

import com.google.gson.annotations.SerializedName

data class SepetYemekResponse(
    @SerializedName("sepet_yemekler")
    val cartFoods: List<SepetYemekModel>,
    
    @SerializedName("success")
    val success: Int
) 