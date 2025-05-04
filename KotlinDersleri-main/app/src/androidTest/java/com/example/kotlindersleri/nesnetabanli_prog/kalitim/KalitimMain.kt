package com.example.kotlindersleri.nesnetabanli_prog.kalitim

fun main() {
    val hayvan = Hayvan()
    val memeli = Memeli()
    val kedi = Kedi()
    val kopek = Kopek()

    hayvan.sesCikar() // kalitim yok, kendi metoduna eristi
    memeli.sesCikar() // kalitim var, bir üst sınıfın metoduna eristi
    kedi.sesCikar() // kalitim var, kendi metoduna eristi
    kopek.sesCikar() // kalitim var, kendi metoduna eristi
}