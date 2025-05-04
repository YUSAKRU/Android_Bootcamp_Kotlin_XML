package com.example.kotlindersleri.odev2

fun icAcilarToplami(kenarSayisi: Int): Int {
    if (kenarSayisi < 3) {
        println("En az üç kenarlı olmalı.")
        return 0
    } else {
        val icAcilarToplami = (kenarSayisi - 2) * 180
        return icAcilarToplami
    }
}

fun main() {
    val kenarSayisi = 5
    val icAcilarToplami = icAcilarToplami(kenarSayisi)
    println("Ic acilar toplami: $icAcilarToplami")
}