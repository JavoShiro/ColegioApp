package com.example.appcolegio

import AgregarAlumnoActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.databinding.ApphomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ApphomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Vincula el diseño con el código utilizando View Binding
        binding = ApphomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtén el usuario actual
        val user = auth.currentUser

        // Verifica si el usuario está logueado
        if (user != null) {
            // Obtén el nombre del usuario en mayúsculas
            val userName = user.displayName?.toUpperCase()
                ?: user.email?.substringBefore("@")?.toUpperCase()

            // Muestra el mensaje de bienvenida
            binding.tvBienvenida.text = "BIENVENIDO, $userName"
        }

        // Configura los botones para redirigir a las actividades correspondientes
        binding.btnNotas.setOnClickListener {
            // Verifica que la Intent para la actividad NotaActivity esté correcta
            val intent = Intent(this, NotaActivity::class.java)
            startActivity(intent)
        }

        binding.btnAgenda.setOnClickListener {
            // Verifica que la Intent para la actividad AgendaActivity esté correcta
            val intent = Intent(this, AgendaActivity::class.java)
            startActivity(intent)
        }
        binding.btnCrearAlumno.setOnClickListener {
            val intent = Intent(this, AgregarAlumnoActivity::class.java)
            startActivity(intent)
        }

    }
}
