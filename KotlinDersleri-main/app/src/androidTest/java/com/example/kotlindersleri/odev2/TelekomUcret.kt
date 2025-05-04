package com.example.kotlindersleri.odev2

fun ucretHesapla(kullanilanMiktarGB: Int): Int {
    val kota = 50
    val sabitUcret = 100
    val asimdaGBBasiAlinanUcret = 4

    var toplamUcret:Int = sabitUcret

    if (kullanilanMiktarGB > kota) {
        val asimdaGB = kullanilanMiktarGB - kota
        toplamUcret = asimdaGB * asimdaGBBasiAlinanUcret + sabitUcret
    } else {
        toplamUcret = sabitUcret
    }

    return toplamUcret
}

fun main() {
    val kullanilanMiktarGB = 100
    val toplamUcret = ucretHesapla(kullanilanMiktarGB)
    println("Toplam Ucret: $toplamUcret")
}