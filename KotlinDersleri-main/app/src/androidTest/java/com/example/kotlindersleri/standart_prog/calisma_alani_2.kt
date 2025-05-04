package com.example.kotlindersleri.standart_prog

fun main() {

    for (i in 1 .. 3) {
        println("dongu1: $i")
    }

    println("-----------------------------------------")

    for (i in 1 .. 21 step 4) {
        println("dongu2: $i")
    }

    println("-----------------------------------------")

    for (i in 20 downTo 0 step 4) {
        println("dongu3: $i")
    }

    println("-----------------------------------------")

    var sayac = 1
    while (sayac < 4) {
        println("sayac: $sayac")
        sayac++
    }

    println("-----------------------------------------")

    for (i in 1..4) {
        if (i == 2) break
        println("dongu5: $i")
    }

    println("-----------------------------------------")

    for (a in 3..7) {
        if (a == 5) continue
        println("dongu6: $a")
    }

}