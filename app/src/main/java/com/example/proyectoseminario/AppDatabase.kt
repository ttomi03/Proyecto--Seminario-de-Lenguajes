package com.example.proyectoseminario
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// La anotación @Database indica que esta clase representa una base de datos de Room.
@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    // Declara una función abstracta para obtener el DAO (Data Access Object) relacionado con la entidad Usuario.
    abstract fun usuarioDao(): UsuarioDao
    // Crea un compañero objeto para proporcionar un acceso Singleton a la base de datos.
    companion object{
        private var INSTANCIA: AppDatabase?= null

        // Esta función obtiene una instancia de la base de datos de Room o la crea si aún no existe.
        fun getDatabase(context: Context): AppDatabase{
            if(INSTANCIA==null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        context, AppDatabase::class.java,"base_app_usuarios")

                        // Permite realizar consultas en el hilo principal (esto puede no ser seguro en producción).
                        .allowMainThreadQueries()
                        // Realiza una migración destructiva si la versión de la base de datos cambia.
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }
    }
}