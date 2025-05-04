package com.example.kotlindersleri.collections

fun main() {
    // HASHSET
    // Tanımlama
    val meyveler = HashSet<String>()

    // Any -> en üst sınıf demek
    // Bütün türleri kapsar.
    // Java -> Object

    meyveler.add("Elma")
    meyveler.add("Muz")
    meyveler.add("Kiraz")
    println(meyveler) // Set'in ilk hali yazdırılır

    meyveler.add("Amasya Elma")
    println(meyveler) // "Amasya Elma" eklendikten sonraki hali yazdırılır

    val y = meyveler.elementAt(2)
    println(y) // Alınan eleman yazdırılır

    println("Boyut : ${meyveler.size}") // Set'in eleman sayısı yazdırılır

    for(meyve in meyveler) {
        println("Sonuc : $meyve")
    }

    for((indeks, meyve) in meyveler.withIndex()) { // Swift : enumerated()
        println("$indeks -> $meyve")
    }
}