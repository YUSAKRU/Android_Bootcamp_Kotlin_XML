package com.eduplayconnect.ashane.data.remote

import com.eduplayconnect.ashane.data.model.CRUDResponse
import com.eduplayconnect.ashane.data.model.SepetYemekResponse
import com.eduplayconnect.ashane.data.model.YemekResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApiService {
    
    @GET("tumYemekleriGetir.php")
    suspend fun getAllFoods(): Response<YemekResponse>
    
    @FormUrlEncoded
    @POST("sepeteYemekEkle.php")
    suspend fun addFoodToCart(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodImageName: String,
        @Field("yemek_fiyat") foodPrice: Int,
        @Field("yemek_siparis_adet") quantity: Int,
        @Field("kullanici_adi") username: String
    ): Response<CRUDResponse>
    
    @FormUrlEncoded
    @POST("sepettekiYemekleriGetir.php")
    suspend fun getCartFoods(
        @Field("kullanici_adi") username: String
    ): Response<SepetYemekResponse>
    
    @FormUrlEncoded
    @POST("sepettenYemekSil.php")
    suspend fun removeFromCart(
        @Field("sepet_yemek_id") cartFoodId: Int,
        @Field("kullanici_adi") username: String
    ): Response<CRUDResponse>
} 