package com.example.proyectoseminario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_table")
class Usuario (
    @ColumnInfo(name = "Nombre") var nombre: String,
    @ColumnInfo(name = "Apellido") var apellido: String,
    @ColumnInfo(name = "Nombre de Usuario") var nombreDeUsuario: String,
    @ColumnInfo(name = "Email") var email: String,
    @ColumnInfo(name = "Password") var password: String,
    @ColumnInfo(name = "Birthdate") var birthdate: String,
){
    @PrimaryKey(autoGenerate = true) var idUsuario: Int = 0
}