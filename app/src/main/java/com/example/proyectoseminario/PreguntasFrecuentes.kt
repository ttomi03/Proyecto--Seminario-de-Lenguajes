package com.example.proyectoseminario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreguntasFrecuentes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas_frecuentes)

        MyToolbarPreguntasfrecuentes().show(this,"Preguntas Frecuentes", false)
    }
}