package com.example.proyectoseminario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Informacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)

        MyToolbarPreguntasfrecuentes().show(this,"Proximos Estrenos", false)

        val tvGoToSerie4 = findViewById<ImageView>(R.id.Serie4)

        tvGoToSerie4.setOnClickListener{

            goToRegister4()
        }

        val tvGoToSerie5 = findViewById<ImageView>(R.id.Serie5)

        tvGoToSerie5.setOnClickListener{

            goToRegister5()
        }

        val tvGoToSerie6 = findViewById<ImageView>(R.id.Serie6)

        tvGoToSerie6.setOnClickListener{

            goToRegister6()
        }
    }

    private fun goToRegister4 (){
        val t= Intent(this, EnterActivity4::class.java)
        startActivity(t)
    }

    private fun goToRegister5 (){
        val t= Intent(this, EnterActivity6::class.java)
        startActivity(t)
    }

    private fun goToRegister6 (){
        val t= Intent(this, EnterActivity5::class.java)
        startActivity(t)
    }
}