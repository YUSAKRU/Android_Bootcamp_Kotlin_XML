package com.example.kotlindersleri.standart_prog

fun main() {
    val yas = 19

    if(yas >= 18){
        println("Oy kullanabilirsiniz!")
    }else{
        println("Oy kullanamazsiniz!")
    }

    val kaullanici_adi = "admin"
    val sifre = 12345
    val sayi = 8

    if(kaullanici_adi == "admin" && sifre == 12345) { // && ifadesi ve anlamında kullanılır.
        println("Hosgeldiniz")
    }else{
        println("Hatalı giris")
    }

    if(sayi == 9 || sayi == 8){ // || ifadesi veya anlamında kullanılır.
        println("Sayi 9 veya 8'e esittir.")
    }else{
        println("Sayi 9 veya 8'e esit degildir.")
    }

    //When, diğer dillerde Switch
    val number = 1

    when(number){
        1 -> println("Sayi birdir.")
        2 -> println("Sayi ikidir")
        else -> println("Bilinmeyen sayi")

    }


}