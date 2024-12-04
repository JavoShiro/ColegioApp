package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón para el rol Profesor
        binding.btclass1.setOnClickListener {
            navigateToLogin("Profesor")
        }

        // Botón para el rol Alumno
        binding.btclass2.setOnClickListener {
            navigateToLogin("Alumno")
        }

        // Botón para el rol Apoderado
        binding.btclass3.setOnClickListener {
            navigateToLogin("Apoderado")
        }
    }

    private fun navigateToLogin(role: String) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("ROL_SELECCIONADO", role)
        startActivity(intent)
    }
}

