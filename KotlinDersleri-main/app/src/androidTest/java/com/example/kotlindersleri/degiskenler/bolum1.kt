package com.example.kotlindersleri.degiskenler

fun main() {
    var id = 1
    var ad = "Mont"
    var resim = "mont.png"
    var puan = 4.7
    var fiyat = 4500
    var stokDurum = true

    println("id: $id")
    println("ad: $ad")
    println("resim: $resim")
    println("puan: $puan")
    println("fiyat: $fiyat")
    println("stokDurum: $stokDurum")

    var mevcutSayi = 10 // Değişken, değeri değiştirilebilir
    mevcutSayi = 20 // Geçerli: Değer güncellendi

    val sabitSayi = 5 // Salt okunur, değeri değiştirilemez
    // sabitSayi = 10 // Hata: val ile tanımlanan bir değişkene yeni değer atanamaz

    val d = 23.54
    val sonuc1 = d.toInt() // Double -> Int dönüşümü
    println("Double to Int : $sonuc1")

    val sonuc2 = d.toDouble()// Double -> Double dönüşümü
    println("Double to Double : $sonuc2")

    val d2 = 1
    val sonuc3 = d2.toChar()// Int -> Char dönüşümü
    println("Int to Char : $sonuc3")

    val sonuc4 = d2.toString() // Int -> String dönüşümü
    println("Int to String : $sonuc4")

    val sayi1 = 10
    val sayi2 = sayi1.toDouble() // int to double dönüşümü
    println("Int to Double: $sayi2")

    val metin1 = "123"
    val metin1ToInt = metin1.toInt() // String to int dönüşümü
    println("String to Int : $metin1ToInt")


    val yazi = "123s"
    val sonuc5 = yazi.toIntOrNull()

    if (sonuc5 != null) {
        println("String to Int donusumu basarili : $sonuc5")
    } else {
        println("String to Int donusumunde hata olustu.")
    }


}