package com.example.proyectoseminario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TerminosCondiciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos_condiciones)

        MyToolbarPreguntasfrecuentes().show(this,"Terminos Y Condiciones", false)
    }
}