package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    val f = Fonksiyonlar()

    // void
    f.selamla1()

    // return
    val gelenSonuc = f.selamla2()
    println("Gelen Sonuc : $gelenSonuc")

    // parametre
    f.selamla3("Zeynep")

    f.topla(32, 2, "Ahmet")
}