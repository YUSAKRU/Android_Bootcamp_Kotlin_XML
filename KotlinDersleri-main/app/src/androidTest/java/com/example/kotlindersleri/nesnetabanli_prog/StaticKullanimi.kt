package com.example.kotlindersleri.nesnetabanli_prog

fun main() {
    val a = ClassA()

    // Standrt nesne tabanlı erişim
    // println(a.x)
    // a.metod()

    // Sanal nesne - Virtual Object - Nameless Object
    // println(ClassA().x)  // 1.Nesne
    // ClassA().metod()  // 2.Nesne


    // Static kullanimi
    println(ClassA.x)  // 1.Nesne
    ClassA.metod()  // 2.Nesne
}