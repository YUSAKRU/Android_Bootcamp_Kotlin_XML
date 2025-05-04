package com.example.kotlindersleri.odev2

fun dikdortgenCevre(kenar1: Double, kenar2: Double): Double {
    val cevre = kenar1*2 + kenar2*2
    return cevre
}

fun main() {
    val kenar1 = 5.0
    val kenar2 = 10.0
    val cevre = dikdortgenCevre(kenar1, kenar2)

    println("Dikdortgenin cevresi: $cevre")
}