package com.example.kotlindersleri.standart_prog

fun main() {

    // 1,2,3
    for (i in 1..3){ // Swift de 1 ... 3 şeklinde kullanılır.
        println("Dongu1: $i")
    }

    // 10 ile 20 arasında 5'er 5'er artsın!
    for (i in 10..20 step 5){
        println("Dongu2: $i")
    }

    // 20 ile 10 arasında 5'er 5'er azalsın!
    for (i in 20 downTo 10 step 5){
        println("Dongu3: $i")
    }

    // 1, 2, 3
    var sayac = 1
    while (sayac < 4){
        println("Dongu4: $sayac")
        sayac++
    }

    for (i in 1..5){
        if (i == 3){
            break // donguyu bitirir.
        }
        println("Dongu5: $i")
    }

    for (i in 1..5) {
        if (i == 3) {
            continue // bulunduğu adımı pas geçer.
        }
        println("Dongu6: $i")
    }

}