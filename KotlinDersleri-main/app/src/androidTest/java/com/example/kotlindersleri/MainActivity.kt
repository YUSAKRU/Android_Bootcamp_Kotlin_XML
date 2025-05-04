package com.example.kotlindersleri

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var str:String? = null
    lateinit var info:String // daha sonra bu degiskene deger atayacagimizi soyluyoruz.
    // lateinit sadece var ile kullanilir. val ile kullanilmaz.
    var x:Int = 0
    var y:Double = 0.0
    var kotrol: Boolean = false
    // lateinit var x:Int // ifadesi kullanilamaz, primitive tiplerde kullanilamaz.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        info = "Bilgi"
    }
}