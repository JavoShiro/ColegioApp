package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btnNotas: Button
    private lateinit var btnAmbienteAprendizaje: Button
    private lateinit var btnLeerConsultas: Button
    private lateinit var btnAgenda: Button
    private lateinit var tvBienvenida: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apphome)

        btnNotas = findViewById(R.id.btnNotas)
        btnAmbienteAprendizaje = findViewById(R.id.btnAmbienteAprendizaje)
        btnLeerConsultas = findViewById(R.id.btnLeerConsultas)
        btnAgenda = findViewById(R.id.btnAgenda)
        tvBienvenida = findViewById(R.id.tvBienvenida)

        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
        tvBienvenida.text = "Bienvenido, $nombreUsuario"

        btnNotas.setOnClickListener {
            val intent = Intent(this, NotaActivity::class.java)
            startActivity(intent)
        }

        btnAmbienteAprendizaje.setOnClickListener {
        }
        // De Momento aun no esta cofigurado

        btnLeerConsultas.setOnClickListener {
        }
        //Aun no funcionara este botón, esta en beta

        btnAgenda.setOnClickListener {
            val intent = Intent(this, AgendaActivity::class.java)
            startActivity(intent)
        }
    }
}
