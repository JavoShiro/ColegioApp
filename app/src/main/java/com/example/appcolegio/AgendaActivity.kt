package com.example.appcolegio

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.app.AlertDialog
import android.widget.EditText
import com.example.appcolegio.databinding.AppagendaBinding // Asegúrate de importar el binding correcto

class AgendaActivity : AppCompatActivity() {

    private lateinit var binding: AppagendaBinding // Usamos el binding correcto
    private val events = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar la vista con ViewBinding
        binding = AppagendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usamos binding para referenciar las vistas
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, events)
        binding.listViewAgenda.adapter = adapter

        binding.btnAddEvent.setOnClickListener {
            showAddEventDialog(adapter)
        }
    }

    private fun showAddEventDialog(adapter: ArrayAdapter<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar Evento")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Agregar") { dialog, which ->
            val event = input.text.toString()
            if (event.isNotEmpty()) {
                events.add(event)
                adapter.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }

        builder.show()
    }
}
