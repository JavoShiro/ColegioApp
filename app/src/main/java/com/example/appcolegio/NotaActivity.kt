package com.example.appcolegio

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotaActivity : AppCompatActivity() {

    private lateinit var btnRegistrarNota: Button
    private lateinit var btnEliminarNota: Button
    private lateinit var btnEditarNota: Button
    private lateinit var llNotas: LinearLayout

    private val nombres = listOf("Felipe López", "Daniela Castro", "Juan Ramos")
    private val notas = mutableMapOf<String, MutableList<Double>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appnota)

        btnRegistrarNota = findViewById(R.id.btnRegistrarNota)
        btnEliminarNota = findViewById(R.id.btnEliminarNota)
        btnEditarNota = findViewById(R.id.btnEditar)
        llNotas = findViewById(R.id.llNotas)

        btnRegistrarNota.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Agregar")
        }

        btnEliminarNota.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Eliminar")
        }

        btnEditarNota.setOnClickListener {
            mostrarDialogoSeleccionarNombre("Editar")
        }
    }

    private fun mostrarDialogoSeleccionarNombre(accion: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Persona")
        builder.setItems(nombres.toTypedArray()) { dialog: DialogInterface, which: Int ->
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
        builder.setPositiveButton("Guardar") { dialog, which ->
            val nota = input.text.toString().toDoubleOrNull()
            if (nota != null && nota in 1.0..7.0) {
                agregarNota(nombre, nota)
            } else {
                mostrarMensaje("La nota debe estar entre 1.0 y 7.0")
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoSeleccionarNota(nombre: String) {
        val notasDePersona = notas[nombre] ?: return mostrarMensaje("No hay notas para $nombre")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Nota a Eliminar para $nombre")
        builder.setItems(notasDePersona.map { it.toString() }.toTypedArray()) { dialog, which ->
            val notaAEliminar = notasDePersona[which]
            eliminarNota(nombre, notaAEliminar)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDialogoSeleccionarNotaParaEditar(nombre: String) {
        val notasDePersona = notas[nombre] ?: return mostrarMensaje("No hay notas para $nombre")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Nota a Editar para $nombre")
        builder.setItems(notasDePersona.map { it.toString() }.toTypedArray()) { dialog, which ->
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
        builder.setPositiveButton("Guardar") { dialog, which ->
            val nuevaNota = input.text.toString().toDoubleOrNull()
            if (nuevaNota != null && nuevaNota in 1.0..7.0) {
                editarNota(nombre, notaActual, nuevaNota)
            } else {
                mostrarMensaje("La nota debe estar entre 1.0 y 7.0")
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun agregarNota(nombre: String, nota: Double) {
        if (!notas.containsKey(nombre)) {
            notas[nombre] = mutableListOf()
        }
        notas[nombre]?.add(nota)
        mostrarNotas()
    }

    private fun eliminarNota(nombre: String, nota: Double) {
        notas[nombre]?.remove(nota)
        mostrarNotas()
        mostrarMensaje("Se eliminó con éxito la nota $nota para $nombre")
    }

    private fun editarNota(nombre: String, notaActual: Double, nuevaNota: Double) {
        val listaNotas = notas[nombre]
        val indiceNota = listaNotas?.indexOf(notaActual)
        if (indiceNota != null && indiceNota != -1) {
            listaNotas[indiceNota] = nuevaNota
            mostrarNotas()
            mostrarMensaje("Se editó con éxito la nota de $notaActual a $nuevaNota para $nombre")
        }
    }

    private fun mostrarNotas() {
        llNotas.removeAllViews()
        for (nombre in nombres) {
            val textoNota = TextView(this)
            val notasDePersona = notas[nombre]?.joinToString(", ") ?: "Sin notas"
            textoNota.text = "$nombre: $notasDePersona"
            textoNota.setPadding(8, 8, 8, 8)
            textoNota.setBackgroundResource(android.R.color.white)
            textoNota.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            llNotas.addView(textoNota)
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}

