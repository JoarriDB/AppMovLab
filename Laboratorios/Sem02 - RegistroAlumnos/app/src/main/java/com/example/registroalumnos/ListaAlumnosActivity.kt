package com.example.registroalumnos

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaAlumnosActivity : AppCompatActivity() {

    private lateinit var recyclerAlumnos: RecyclerView
    private lateinit var spinnerOrden: Spinner
    private lateinit var btnVolver: Button
    private lateinit var adaptador: AdaptadorAlumnos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_lista_alumnos)

        // Enlazar vistas
        recyclerAlumnos = findViewById(R.id.recyclerAlumnos)
        spinnerOrden = findViewById(R.id.spinnerOrden)
        btnVolver = findViewById(R.id.btnVolver)

        // Crear adaptador de alumnos
        adaptador = AdaptadorAlumnos(MainActivity.listaAlumnos.toMutableList())
        recyclerAlumnos.layoutManager = LinearLayoutManager(this)
        recyclerAlumnos.adapter = adaptador

        // Opciones del Spinner
        val opciones = arrayOf("Ordenar por Código", "Ordenar por Apellidos")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrden.adapter = adapterSpinner

        // Listener del Spinner
        spinnerOrden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val listaOrdenada = when (position) {
                    0 -> MainActivity.listaAlumnos.sortedBy { it.codigo }
                    1 -> MainActivity.listaAlumnos.sortedBy { it.apellidos }
                    else -> MainActivity.listaAlumnos
                }
                adaptador.actualizarLista(listaOrdenada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Botón para volver
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
