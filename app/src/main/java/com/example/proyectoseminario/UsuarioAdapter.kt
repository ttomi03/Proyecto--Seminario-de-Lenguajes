package com.example.proyectoseminario

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UsuarioAdapter(private var usuarios: MutableList<Usuario>, var context: Context): RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val item = usuarios[position]
        holder.txtNombreDeUsuario.text = item.nombreDeUsuario

        holder.itemView.setOnClickListener {
            // Llama a la función para iniciar sesión con las credenciales del usuario seleccionado
            Log.d("UsuarioAdapter", "Item clickeado: ${item.nombreDeUsuario}")
            iniciarSesion(item.email, item.password)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuarios, parent, false)
        return UsuarioViewHolder(view)
    }
    override fun getItemCount() = usuarios.size



    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombreDeUsuario: TextView

        init {
            txtNombreDeUsuario = view.findViewById(R.id.tvNombreDeUsuario)
        }
    }

    private fun iniciarSesion(email: String, password: String) {
        if (esCredencialValida(email, password)) {
            // Iniciar sesión exitosamente
            Toast.makeText(context, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
            irAMainActivity()
        } else {
            // Credenciales incorrectas
            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }


    }
    private fun esCredencialValida(email: String, password: String): Boolean {
        val usuario = AppDatabase.getDatabase(context).usuarioDao().findUsuarioByEmailAndPassword(email, password)
        return usuario != null
    }

    private fun irAMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}