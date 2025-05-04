package com.example.kotlindersleri.nesnetabanli_prog

class ClassB : MyInterface {
    override var degisken: Int = 100

    override fun metod1() {
        println("Metod 1 calisti!")
    }

    override fun metod2(): String {
        return "Metod 2 calisti!"
    }

}

