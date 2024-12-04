package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.databinding.AppsesionBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: AppsesionBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppsesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val correo = binding.email.text.toString()  // Usando ViewBinding
            val contrasena = binding.password.text.toString()  // Usando ViewBinding

            if (correo.isNotEmpty() && contrasena.isNotEmpty()) {
                // Iniciar sesión con Firebase Authentication
                auth.signInWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Obtener el usuario actual
                            val user = auth.currentUser
                            if (user != null) {
                                // Lógica para redirigir según el dominio del correo
                                when {
                                    correo.endsWith("@colegio.com") -> {
                                        if (correo.contains(".prof@colegio.com")) {
                                            // Redirigir a HomeActivity para el profesor
                                            val intent = Intent(this, HomeActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else if (correo.contains(".alum@colegio.com")) {
                                            // Redirigir a Home2Activity para el alumno
                                            val intent = Intent(this, Home2Activity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(this, "Correo no reconocido", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    else -> {
                                        Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

