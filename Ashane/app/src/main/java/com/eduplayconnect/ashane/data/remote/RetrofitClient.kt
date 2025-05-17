package com.eduplayconnect.ashane.data.remote

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    // Create a Gson instance that can handle problematic responses
    private val gson = GsonBuilder()
        .setLenient() // This helps with malformed JSON
        .serializeNulls() // Include null fields in serialization
        .registerTypeAdapter(
            object : TypeToken<List<Any>>() {}.type,
            object : JsonDeserializer<List<Any>> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): List<Any> {
                    // Handle case where API returns an empty string or null instead of an array
                    if (json == null || json.isJsonNull || 
                        (json.isJsonPrimitive && json.asString.isBlank())) {
                        return emptyList()
                    }
                    
                    // Otherwise, let Gson handle it normally
                    val result = mutableListOf<Any>()
                    if (json.isJsonArray) {
                        json.asJsonArray.forEach { element ->
                            context?.deserialize<Any>(element, Any::class.java)?.let {
                                result.add(it)
                            }
                        }
                    }
                    return result
                }
            }
        )
        .create()
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
    
    val foodApiService: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
} 