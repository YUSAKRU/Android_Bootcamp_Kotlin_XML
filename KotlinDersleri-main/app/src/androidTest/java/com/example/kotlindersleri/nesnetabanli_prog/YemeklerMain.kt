package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    // Nesne olusturma
    val y1 = Yemekler(100, "Kofte", 250)
    // Deger okuma
    println("Id : ${y1.id}")
    println("Ad : ${y1.ad}")
    println("Fiyat : ${y1.fiyat}")
    // Deger atama
    y1.fiyat = 300
    println("Fiyat : ${y1.fiyat}")
    y1.bilgiAl()



    val y2 = Yemekler(101, "Tavuk", 345)
    y2.bilgiAl()
    y2.ad = "Tavuk"
    y2.bilgiAl()
}