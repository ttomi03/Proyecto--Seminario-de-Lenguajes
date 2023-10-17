package com.example.proyectoseminario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    // Definición Variables
    private lateinit var etNombreSignUp: EditText
    private lateinit var etApellidoSignUp: EditText
    private lateinit var etNombreDeUsuarioSignUp: EditText
    private lateinit var etEmailSignUp: EditText
    private lateinit var etPassSignUp: EditText
    private lateinit var etPassConfirmSignUp: EditText
    private lateinit var etBirthdateSignUp: EditText
    private lateinit var btnCrearUsuarioSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Asignando elemento a variables
        etNombreSignUp = findViewById(R.id.etNombreSignUp)
        etApellidoSignUp = findViewById(R.id.etApellidoSignUp)
        etNombreDeUsuarioSignUp = findViewById(R.id.etNombreDeUsuarioSignUp)
        etEmailSignUp = findViewById(R.id.etEmailSignUp)
        etPassSignUp = findViewById(R.id.etPassSignUp)
        etPassConfirmSignUp = findViewById(R.id.etPassConfirmSignUp)
        etBirthdateSignUp = findViewById(R.id.etBirthdateSignUp)
        btnCrearUsuarioSignUp = findViewById(R.id.btnCrearUsuarioSignUp)

        //Botón Crear Usuario
        btnCrearUsuarioSignUp.setOnClickListener {
            // Falta de Datos
            if(etNombreSignUp.text.isEmpty()||etApellidoSignUp.text.isEmpty()||
                etNombreDeUsuarioSignUp.text.isEmpty()|| etEmailSignUp.text.isEmpty()||
                etPassSignUp.text.isEmpty()||etPassConfirmSignUp.text.isEmpty()||
                etBirthdateSignUp.text.isEmpty()){
                Toast.makeText(this, "Faltan Datos.",Toast.LENGTH_SHORT).show()
            }else{

                //Email NO válido
                if(!isValidEmail(etEmailSignUp.text.toString())){
                    Toast.makeText(this, "Ingrese un Email válido.",Toast.LENGTH_SHORT).show()
                }else{
                    // Contraseña
                    if(etPassSignUp.text.toString()!=etPassConfirmSignUp.text.toString()){
                        Toast.makeText(this, "Las contraseñas no coinciden.",Toast.LENGTH_SHORT).show()

                    }else{
                        //Datos Correctos

                        //Guardando datos
                        val nuevoUsuario = Usuario(etNombreSignUp.text.toString(),etApellidoSignUp.text.toString(),
                            etNombreDeUsuarioSignUp.text.toString(),etEmailSignUp.text.toString(),
                            etPassSignUp.text.toString(),etBirthdateSignUp.text.toString())

                        AppDatabase.getDatabase(this).usuarioDao().insertUsuario(nuevoUsuario)

                        Toast.makeText(this, "Usuario Creado.",Toast.LENGTH_SHORT).show()
                        irAMainActivity()
                    }
                }
            }
        }

    }

    // Validación de Email
    private fun isValidEmail(email: String): Boolean{
        val pattern = Patterns.EMAIL_ADDRESS  //Cargo patrón de email
        return pattern.matcher(email).matches() //comparo pattern con email [pattern.matcher(email)],
                                                //devuelvo boolean [matches()]
    }
    private fun irAMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //Función onBackPressed
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}