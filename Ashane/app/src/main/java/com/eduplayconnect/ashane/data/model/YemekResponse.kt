package com.eduplayconnect.ashane.data.model

import com.google.gson.annotations.SerializedName

data class YemekResponse(
    @SerializedName("yemekler")
    val foods: List<YemekModel>,
    
    @SerializedName("success")
    val success: Int
) 