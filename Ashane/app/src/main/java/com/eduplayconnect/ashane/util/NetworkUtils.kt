package com.eduplayconnect.ashane.util

import android.util.Log
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkUtils {
    
    private const val TAG = "NetworkUtils"
    
    /**
     * Handles common network exceptions and returns a user-friendly error message
     * 
     * @param throwable The exception to handle
     * @return A user-friendly error message
     */
    fun handleNetworkException(throwable: Throwable): String {
        Log.e(TAG, "Network error", throwable)
        
        return when (throwable) {
            is UnknownHostException -> "Sunucuya bağlanılamıyor. İnternet bağlantınızı kontrol edin."
            is SocketTimeoutException -> "Bağlantı zaman aşımına uğradı. Lütfen daha sonra tekrar deneyin."
            is IOException -> "Ağ hatası: İnternet bağlantınızı kontrol edin."
            is JsonSyntaxException -> "Sunucudan gelen yanıt işlenemedi (JSON hatası)."
            is JsonIOException -> "Sunucudan gelen yanıt beklenmedik bir şekilde sonlandı."
            else -> "Beklenmeyen bir hata oluştu: ${throwable.message}"
        }
    }
} 