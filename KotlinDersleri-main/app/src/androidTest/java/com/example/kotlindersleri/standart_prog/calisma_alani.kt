package com.example.kotlindersleri.standart_prog

fun main() {
    val kullanici_yasi: Int = 11

    if (kullanici_yasi < 18) {
        println("Kullanici girisi icin -yas- yetkiniz saglanamadi!")
    } else {
        println("Yas sarti saglandi, kullanici girisi yapabilirsiniz!")
    }

    println("-----------------------------------------")

    val kullanici_adi: String = "admin"
    val kullanici_sifre: String = "12345"

    if (kullanici_adi == "admin" && kullanici_sifre == "12345") {
        println("Kullanici adi ve sifre dogru, giris yapildi!")
    } else {
        println("Kullanici adi veya sifre yanlis, giris yapilamadi!")
    }

    println("-----------------------------------------")

    if (kullanici_yasi == 18 || kullanici_yasi == 19) {
        println("Kullanici yetiskinlige adim atıyor.")
    } else if (kullanici_yasi < 18) {
        println("Kullanici coccuk, ehliyet alamaz!")
    } else { println("Bu yasa kadar ne bekledin? Ehliyet alabilirsin!") }

    println("-----------------------------------------")

    var sayi = 2

    when (sayi) {
        1 -> println("menu 1")
        2 -> println("menu 2")
        else -> println("menu listesi harici secim")
    }

}

