package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    // Nullable, Null Safety, Optional(swift)
    // Genellikle mobil uygulama geliştirme dillerinde yer almaktadır.
    // null, NaN, nil

    // 1. Tanimlama
    var mesaj: String? = null

    //mesaj = "Merhaba"

    // Yontem 1
    // sorun yoksa calisir, sorun yoksa uygulama cokmez
    println("Yontem 1: ${mesaj?.uppercase()}")

    val x = mesaj?.uppercase()
    //x.lowercase()

    // Yontem 2
    // sorun yoksa calisir, sorun varsa coker
    // cok emin olduğun kodlarda kullanilabilir
    //println("Yontem 2: ${mesaj!!.uppercase()}")

    // Yontem 3
    // null kontrol
    if (mesaj != null){
        println("Yontem 3: ${mesaj.uppercase()}")
    } else println("Yontem 3: Degiskende null yer almaktadir!")

    mesaj?.let {
        println("Yontem 3.1: ${mesaj.uppercase()}")
    } //else'i yok!!!


}