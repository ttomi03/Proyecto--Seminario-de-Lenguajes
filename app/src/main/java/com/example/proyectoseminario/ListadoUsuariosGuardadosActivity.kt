package com.example.proyectoseminario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ListadoUsuariosGuardadosActivity : AppCompatActivity() {

    private lateinit var rvUsuarios: RecyclerView
    private lateinit var usuariosAdapter: UsuarioAdapter
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_usuarios_guardados)

        // Inicializa los elementos de la interfaz de usuario y establece la barra de herramientas personalizada.
        rvUsuarios = findViewById(R.id.rvUsuarios)
        usuariosAdapter = UsuarioAdapter(getUsuarios(),this)
        rvUsuarios.adapter = usuariosAdapter
        toolbar = findViewById(R.id.toolbar)
        toolbar.title ="" // Oculta el título en la barra de herramientas
        setSupportActionBar(toolbar)

    }

    // Esta función obtiene una lista de usuarios desde la base de datos utilizando AppDatabase.
    private fun getUsuarios(): MutableList<Usuario>{
        val usuarios: MutableList<Usuario> = ArrayList()
        usuarios.addAll(AppDatabase.getDatabase(this).usuarioDao().getAll())
        return usuarios
    }

    
}