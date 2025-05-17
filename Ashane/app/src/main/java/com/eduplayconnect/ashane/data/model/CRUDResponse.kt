package com.eduplayconnect.ashane.data.model

import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("success")
    val success: Int = 0,
    
    @SerializedName("message")
    val message: String = ""
) 