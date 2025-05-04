package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    ucretHesapla(KonserveBoyut.ORTA, 55)
}

fun ucretHesapla(boyut: KonserveBoyut, adet: Int){
    when(boyut){
        KonserveBoyut.KUCUK -> println("Toplam fiyat: ${adet * 12}")
        KonserveBoyut.ORTA -> println("Toplam fiyat: ${adet * 16}")
        KonserveBoyut.BUYUK -> println("Toplam fiyat: ${adet * 20}")


    }

}