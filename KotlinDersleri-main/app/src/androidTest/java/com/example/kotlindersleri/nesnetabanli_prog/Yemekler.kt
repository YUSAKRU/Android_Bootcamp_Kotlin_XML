package com.example.kotlindersleri.nesnetabanli_prog

data class Yemekler(var id:Int, var ad:String, var fiyat:Int) {
    // Constructor - init fonksiyonu
    // Bu sınıftan nesne oluştuğunda çalışsın.
    init {
        println("****** Nesne Oluşturuldu ******")
    }

    fun bilgiAl(){
        println("Yemek id: ${id}")
        println("Yemek ad: ${ad}")
        println("Yemek fiyat: ${fiyat}")
    }
    // this : Bulunduğu sınıfı temsil eder. Swift dilinde self
    // super : Kalıtım ile bir üst sınıfı temsil eder.
}