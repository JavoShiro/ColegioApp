package com.example.appcolegio

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.databinding.AppnotaBinding
import com.google.firebase.auth.FirebaseAuth

class NotaActivity : AppCompatActivity() {

    private lateinit var binding: AppnotaBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding
        binding = AppnotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Cargar la información de los alumnos (simulado o basado en el tipo de usuario)
        cargarAlumnos()

        // Configura los listeners para los botones
        binding.btnRegistrarNota.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Agregar")
        }

        binding.btnEliminarNota.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Eliminar")
        }

        binding.btnEditar.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Editar")
        }
    }

    private fun cargarAlumnos() {
        // Simula la carga de alumnos basado en el correo electrónico del usuario actual
        val user = auth.currentUser
        if (user != null) {
            val correo = user.email
            if (correo != null) {
                if (correo.contains(".prof@colegio.com")) {
                    // Cargar lista de alumnos solo si el usuario es profesor
                    mostrarNotas()
                } else {
                    mostrarMensaje("No tienes acceso para administrar notas")
                }
            }
        }
    }

    private fun mostrarDialogoSeleccionarNombre(accion: String) {
        // Aquí ya no utilizamos Firestore, simplemente mostramos un nombre fijo de ejemplo
        val nombres = listOf("Juan Pérez", "Ana Gómez", "Carlos Sánchez") // Lista de ejemplo

        if (nombres.isEmpty()) {
            mostrarMensaje("No hay alumnos registrados.")
            return
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Persona")
        builder.setItems(nombres.toTypedArray()) { _, which ->
            val nombreSeleccionado = nombres[which]
            when (accion) {
                "Agregar" -> mostrarDialogoIngresarNota(nombreSeleccionado)
                "Eliminar" -> mostrarDialogoSeleccionarNota(nombreSeleccionado)
                "Editar" -> mostrarDialogoSeleccionarNotaParaEditar(nombreSeleccionado)
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoIngresarNota(nombre: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ingresar Nota para $nombre")
        val input = EditText(this)
        builder.setView(input)
        builder.setPositiveButton("Guardar") { _, _ ->
            val nota = input.text.toString().toDoubleOrNull()
            if (nota != null && nota in 1.0..7.0) {
                mostrarMensaje("Nota de $nombre agregada con éxito.")
            } else {
                mostrarMensaje("La nota debe estar entre 1.0 y 7.0")
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoSeleccionarNota(nombre: String) {
        // Simulamos que el alumno tiene algunas notas predefinidas
        val notasDePersona = listOf(5.5, 6.0, 4.0) // Ejemplo de notas

        if (notasDePersona.isEmpty()) {
            mostrarMensaje("No hay notas para $nombre")
            return
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Nota a Eliminar para $nombre")
        builder.setItems(notasDePersona.map { it.toString() }.toTypedArray()) { _, which ->
            val notaAEliminar = notasDePersona[which]
            eliminarNota(nombre, notaAEliminar)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoSeleccionarNotaParaEditar(nombre: String) {
        // Simulamos que el alumno tiene algunas notas predefinidas
        val notasDePersona = listOf(5.5, 6.0, 4.0) // Ejemplo de notas

        if (notasDePersona.isEmpty()) {
            mostrarMensaje("No hay notas para $nombre")
            return
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Nota a Editar para $nombre")
        builder.setItems(notasDePersona.map { it.toString() }.toTypedArray()) { _, which ->
            val notaActual = notasDePersona[which]
            mostrarDialogoEditarNota(nombre, notaActual)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoEditarNota(nombre: String, notaActual: Double) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Editar Nota para $nombre")
        val input = EditText(this)
        input.setText(notaActual.toString())
        builder.setView(input)
        builder.setPositiveButton("Guardar") { _, _ ->
            val nuevaNota = input.text.toString().toDoubleOrNull()
            if (nuevaNota != null && nuevaNota in 1.0..7.0) {
                mostrarMensaje("Nota de $nombre editada con éxito.")
            } else {
                mostrarMensaje("La nota debe estar entre 1.0 y 7.0")
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun eliminarNota(nombre: String, nota: Double) {
        // Lógica para eliminar nota (simulada)
        mostrarMensaje("Nota de $nombre eliminada con éxito.")
    }

    private fun mostrarNotas() {
        // Simulamos mostrar notas de los alumnos
        val nombres = listOf("Juan Pérez", "Ana Gómez", "Carlos Sánchez") // Ejemplo de alumnos
        binding.llNotas.removeAllViews()
        for (nombre in nombres) {
            val textoNota = TextView(this)
            textoNota.text = "$nombre: 5.5, 6.0, 4.0" // Ejemplo de notas
            textoNota.setPadding(8, 8, 8, 8)
            textoNota.setBackgroundResource(android.R.color.white)
            textoNota.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            binding.llNotas.addView(textoNota)
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}
