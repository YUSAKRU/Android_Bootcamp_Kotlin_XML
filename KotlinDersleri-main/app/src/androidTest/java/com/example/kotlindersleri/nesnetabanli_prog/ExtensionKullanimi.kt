package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    val sonuc = 5 carp 3 // 5.carp(3)
    println(sonuc)
}

infix fun Int.carp(sayi:Int) : Int {
    return this * sayi // this : Fonksiyonun çağrıldığı nesneyi temsil eder.
}