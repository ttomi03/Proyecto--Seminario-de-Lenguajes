package com.example.proyectoseminario

import androidx.appcompat.app.AppCompatActivity

class MyToolbarPreguntasfrecuentes {

    fun show (activities : AppCompatActivity, title: String, upButton: Boolean)
    {
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar2))
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}