package com.example.kotlindersleri.collections

fun main() {
    val f1 = Filmler(1, "Interstellar", 50)
    val f2 = Filmler(2, "The Hateful Eight", 40)
    val f3 = Filmler(3, "The Pianist", 30)

    val filmlerListesi = ArrayList<Filmler>()
    filmlerListesi.add(f1)
    filmlerListesi.add(f2)
    filmlerListesi.add(f3)

    for (film in filmlerListesi) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }

    // Sıralama - Sorting
    println("--- Fiyata Gore Artan ---")
    // Ascend -ASC
    val siralama1 = filmlerListesi.sortedWith(compareBy{ it.fiyat })
    for (film in siralama1) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }

    println("--- Fiyata Gore Arzalan ---")
    // DESC - Descending
    val siralama2 = filmlerListesi.sortedWith(compareByDescending{ it.fiyat })
    for (film in siralama2) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }

    // Filtreleme
    println("--- Filtreleme 1 ---")
    val filtreleme1 = filmlerListesi.filter { it.fiyat > 40  }
    for (film in filtreleme1) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }

    println("--- Filtreleme 2 ---")
    val filtreleme2 = filmlerListesi.filter { it.fiyat > 40 && it.ad.contains("Inter")  }
    for (film in filtreleme2) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }


    println("--- Filtreleme 3 ---")
    val filtreleme3 = filmlerListesi.filter { it.ad.contains("Inter") }
    for (film in filtreleme3) {
        println("${film.id} : ${film.ad} : ${film.fiyat}")
    }

}
