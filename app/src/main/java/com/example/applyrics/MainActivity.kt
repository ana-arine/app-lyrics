package com.example.applyrics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        buscarMusica()
    }

    fun buscarMusica() {
        Handler(
            Looper.getMainLooper()).postDelayed({
            val telaBuscarMusica =
                Intent(this, Activity_Tela_Fragments::class.java)
            startActivity(telaBuscarMusica)
            finish()
        }, 3000)}
}
