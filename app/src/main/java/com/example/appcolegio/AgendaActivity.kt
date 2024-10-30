package com.example.appcolegio

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.app.AlertDialog
import android.widget.EditText

class AgendaActivity : AppCompatActivity() {

    private lateinit var listViewAgenda: ListView
    private lateinit var btnAddEvent: Button
    private val events = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appagenda)

        listViewAgenda = findViewById(R.id.listViewAgenda)
        btnAddEvent = findViewById(R.id.btnAddEvent)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, events)
        listViewAgenda.adapter = adapter

        btnAddEvent.setOnClickListener {
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
