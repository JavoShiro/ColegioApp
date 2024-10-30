package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.R

class LoginActivity : AppCompatActivity() {

    private lateinit var etRutOrEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appsesion)

        etRutOrEmail = findViewById(R.id.etRutOrEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val rutOrEmail = etRutOrEmail.text.toString()
            val password = etPassword.text.toString()

            if (rutOrEmail.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("NOMBRE_USUARIO", rutOrEmail)
                startActivity(intent)
            } else if (rutOrEmail.isEmpty()) {
                Toast.makeText(this, "Por favor de introducir un nombre para continuar", Toast.LENGTH_SHORT).show()
                //Mensaje para que muestre que debe completar el campo de Nombre (De momento no esta la base de datos{
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                //mensaje para mostrar que fata los campos
            }
        }
    }
}
