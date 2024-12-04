package com.example.appcolegio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.databinding.Apphome2Binding

class Home2Activity : AppCompatActivity() {

    private lateinit var binding: Apphome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Apphome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del nombre de usuario
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
        binding.tvBienvenida.text = "Bienvenido, $nombreUsuario"

        // Acciones para los botones
        binding.btnNotas.setOnClickListener {
            // Navegar a NotasActivity
        }

        binding.btnAmbienteAprendizaje.setOnClickListener {
            // Acción para el botón de Ambiente Aprendizaje
        }

        binding.btnAsistencia.setOnClickListener {
            // Acción para el botón de Asistencia
        }

        binding.btnAgenda.setOnClickListener {
            // Navegar a AgendaActivity
        }

        // Mostrar el calendario si es necesario
        binding.calendarView.visibility = android.view.View.VISIBLE
    }
}
