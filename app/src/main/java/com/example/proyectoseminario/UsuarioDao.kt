package com.example.proyectoseminario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuarioDao {
    // Consulta para obtener todos los usuarios
    @Query("select * from usuario_table")
    fun getAll(): List<Usuario>

    // Operación de inserción para agregar un nuevo usuario a la base de datos
    @Insert
    fun insertUsuario(usuario: Usuario)

    // Consulta para buscar un usuario por su email y contraseña
    //@Query("SELECT * FROM usuarios WHERE email = :email AND contraseña = :password")
    @Query("select * from usuario_table where email = :email and password = :password")
    fun findUsuarioByEmailAndPassword(email: String, password: String): Usuario?

}