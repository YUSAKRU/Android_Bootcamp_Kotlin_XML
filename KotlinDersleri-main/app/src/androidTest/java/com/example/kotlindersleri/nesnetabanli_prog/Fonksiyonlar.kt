package com.example.kotlindersleri.nesnetabanli_prog

class Fonksiyonlar {
    // void - sadece islem yapan
    fun selamla1() { // Swift dilinde --> func
        val sonuc = "Merhaba Ahmet"
        println(sonuc)
    }

    // return - hem işlem yapan hem de sonucu döndüren
    fun selamla2() : String {
        val sonuc = "Merhaba Ahmet"
        return sonuc
    }

    // parametre
    fun selamla3(isim:String) {
        val sonuc = "Merhaba $isim"
        println(sonuc)
    }


    // overloading - Bir sinif içerisinde ayni isimde birden fazla fonksiyon tanımlama
    fun topla(sayi1:Int, sayi2:Int) {
        println("İki Int sayinin toplami : ${sayi1+sayi2}")
    }

    fun topla(sayi1:Double, sayi2:Double) {
        println("İki Int sayinin toplami : ${sayi1+sayi2}")
    }

    fun topla(sayi1:Int, sayi2:Int, isim:String) {
        println("İki Int sayinin toplami : ${sayi1+sayi2} - Islem Yapan : $isim")
    }
}