package com.example.kotlindersleri.odev2

fun aHarfiSayisiBul(keime: String): Int {
    val aSayisi = keime.count { it == 'a' || it == 'A' }
    return aSayisi
}

fun main() {
    val kelime = "Selamünaleyküm"
    val aSayisi = aHarfiSayisiBul(kelime)
    println("Kelimenin icinde $aSayisi adet 'a' harfi vardır.")

}