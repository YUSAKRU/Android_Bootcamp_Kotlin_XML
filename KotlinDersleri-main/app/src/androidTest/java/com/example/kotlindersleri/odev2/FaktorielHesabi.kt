package com.example.kotlindersleri.odev2

fun faktorielHesapla(sayi: Int): Int {
    if (sayi < 0) {
        println("Negatif sayıların faktöriyeli hesaplanamaz.")
        return -1
    }

    if (sayi == 0 || sayi == 1) {
        return 1
    } else return sayi * faktorielHesapla(sayi - 1)
}

fun main() {
    var sayi: Int = (1..10).random()
    println("Hesaplanacak sayi: $sayi")
    val faktoriyel = faktorielHesapla(sayi)
    println("$sayi! = $faktoriyel")
}