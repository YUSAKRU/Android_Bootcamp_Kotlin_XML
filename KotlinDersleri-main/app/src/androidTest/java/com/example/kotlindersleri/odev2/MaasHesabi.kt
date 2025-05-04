package com.example.kotlindersleri.odev2

fun maasHesabi(gunSayisi: Int): Int {
    val calismaSaatiGunluk = 8
    val saatUcreti = 10
    val mesaiSaatUcreti = 20
    val mesaiSiniri = 160

    val toplamCalismaSaati = gunSayisi * calismaSaatiGunluk

    var toplamMaas: Int = 0

    if (toplamCalismaSaati <= mesaiSiniri) {
        toplamMaas = toplamCalismaSaati * saatUcreti
    } else {
        val mesaiSaati = toplamCalismaSaati - mesaiSiniri
        val mesaiMaasi = mesaiSaati * mesaiSaatUcreti
        val calismaSaati = toplamCalismaSaati - mesaiSaati
        val calismaMaasi = calismaSaati * saatUcreti
        toplamMaas = mesaiMaasi + calismaMaasi
    }

    return toplamMaas
}

fun main() {
    val gunSayisi = 200
    val toplamMaas = maasHesabi(gunSayisi)
    println("Toplam Maas: $toplamMaas")

}