package com.example.proyectoseminario

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class LoginActivity : AppCompatActivity() {

    // Creando variables
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnCrearUsuario: Button
    private lateinit var cbRecordarUsuario: CheckBox
    private lateinit var toolbar: Toolbar
    private lateinit var preferencias: SharedPreferences







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Asignando elementos a Variables
        etEmail = findViewById(R.id.etEmailLogin)
        etPass = findViewById(R.id.etPassLogin)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesionLogin)
        btnCrearUsuario = findViewById(R.id.btnCrearUsuarioLogin)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title ="" // Oculta el título en la barra de herramientas
        setSupportActionBar(toolbar)


        preferencias = getSharedPreferences("sp_credenciales", Context.MODE_PRIVATE)

        // Leer valores de SharedPreferences y establecerlos en los campos
        var emailGuardado = preferencias.getString("Email", "")
        var passwordGuardada = preferencias.getString("Password", "")

        etEmail.setText(emailGuardado)
        etPass.setText(passwordGuardada)

        crearCanalNotificacion ()




        // Botón Iniciar Sesion
        btnIniciarSesion.setOnClickListener {

            // Datos faltantes
            if(etEmail.text.isEmpty()||etPass.text.isEmpty()){
                Toast.makeText(this, "Faltan datos.", Toast.LENGTH_SHORT).show()
            }else{
                //Email NO valido
                if(!isValidEmail(etEmail.text.toString())){
                    Toast.makeText(this, "Ingrese un Email válido.",Toast.LENGTH_SHORT).show()
                }else{
                    // Todos los datos ingresados: Buscando usuario por email y contraseña
                    val usuario = AppDatabase.getDatabase(this).usuarioDao().findUsuarioByEmailAndPassword(etEmail.text.toString(), etPass.text.toString())

                    if (usuario != null) {
                        // Usuario encontrado, iniciar sesión
                        Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show()

                        if (cbRecordarUsuario.isChecked) {
                            emailGuardado = etEmail.text.toString() // Guarda el email actual en SharedPreferences
                            passwordGuardada = etPass.text.toString() // Guarda la contraseña actual en SharedPreferences

                            // Editor de SharedPreferences para realizar cambios
                            val editor = preferencias.edit()


                            editor.putString("Email", emailGuardado)
                            editor.putString("Password", passwordGuardada)

                            // Aplica los cambios
                            editor.apply()

                            createSimpleNotification ()
                        }

                        irAMainActivity() // Ingresar a la MainActivity
                    } else {
                        // Usuario no encontrado, muestra un mensaje de error
                        Toast.makeText(this, "Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
        // Botón Crear Usuario
        //  -Ir a pantalla de Registro de Usuario
        btnCrearUsuario.setOnClickListener {
            irASignInActivity()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_listado){
            val intentListado = Intent(this,ListadoUsuariosGuardadosActivity::class.java)
            startActivity(intentListado)
        }

        return super.onOptionsItemSelected(item)
    }
    // Función para ir a la pantalla de Registro de Usuario
    private fun irASignInActivity(){
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }
    private fun isValidEmail(email: String): Boolean{
        val pattern = Patterns.EMAIL_ADDRESS  //Cargo patrón de email
        return pattern.matcher(email).matches() //comparo pattern con email [pattern.matcher(email)],
        //devuelvo boolean [matches()]
    }
    private fun irAMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("moveTaskToBack(true)"))
    override fun onBackPressed() {
        moveTaskToBack(true) // Cierra la aplicación y la coloca en segundo plano
    }

    private fun crearCanalNotificacion () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Recordaste tu usuario "
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object{
        const val MY_CHANNEL_ID = "myhcannel"
    }

    private fun createSimpleNotification (){
        val builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_mail_24)
            .setContentTitle("Recordaste el usuario")
            .setContentText("Al volver a entrar a la app no se pediran tus datos :)")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@LoginActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
            notify(1, builder.build())
        }


    }


}