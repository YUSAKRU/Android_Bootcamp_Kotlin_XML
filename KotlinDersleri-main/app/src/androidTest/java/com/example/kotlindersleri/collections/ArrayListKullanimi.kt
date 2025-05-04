package com.example.kotlindersleri.collections

fun main() {
    // tanimlama
    val meyveler = ArrayList<String>()

    // Veri ekleme : listenin en sonuna ekler
    meyveler.add("Elma") //0.
    meyveler.add("Kiraz") //1.
    meyveler.add("muz") //2.
    println(meyveler)
    println(meyveler.size)

    // güncelleme
    meyveler[2] = "Muz"
    println(meyveler)

    // Insert : istediğimiz bir indekse veri ekleme
    // mevcut olarak o indekste bulunan deger ve sonraki indeks değerlerini bir artırır.
    meyveler.add(1, "Kivi")
    println(meyveler)

    // okuma
    val m = meyveler.get(3)
    println(m)
    println(meyveler[3])

    println("Boyut : ${meyveler.size}")

    meyveler.reverse()
    println(meyveler)

    for (meyve in meyveler) {
        println("Sonuc: $meyve")
    }

    for((indeks,meyve) in meyveler.withIndex()){
        println("$indeks. -> $meyve")
    }

    meyveler.removeAt(2)
    println(meyveler)

    meyveler.clear()
    println(meyveler)

}