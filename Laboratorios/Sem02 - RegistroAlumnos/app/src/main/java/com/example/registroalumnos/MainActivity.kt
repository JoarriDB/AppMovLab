package com.example.registroalumnos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtCodigo: EditText
    private lateinit var edtApellidos: EditText
    private lateinit var edtNombres: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnVerLista: Button

    companion object {
        val listaAlumnos = mutableListOf<Alumno>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        edtCodigo = findViewById(R.id.edtCodigo)
        edtApellidos = findViewById(R.id.edtApellidos)
        edtNombres = findViewById(R.id.edtNombres)
        edtCorreo = findViewById(R.id.edtCorreo)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnVerLista = findViewById(R.id.btnVerLista)

        btnRegistrar.setOnClickListener {
            val codigo = edtCodigo.text.toString().trim()
            val apellidos = edtApellidos.text.toString().trim()
            val nombres = edtNombres.text.toString().trim()
            val correo = edtCorreo.text.toString().trim()

            val soloLetras = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
            val correoValido = android.util.Patterns.EMAIL_ADDRESS

            // Validaciones
            if (codigo.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (codigo.length != 10 || !codigo.all { it.isDigit() }) {
                Toast.makeText(this, "El código debe tener 10 dígitos numéricos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!soloLetras.matches(apellidos)) {
                Toast.makeText(this, "Los apellidos solo deben contener letras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!soloLetras.matches(nombres)) {
                Toast.makeText(this, "Los nombres solo deben contener letras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!correoValido.matcher(correo).matches()) {
                Toast.makeText(this, "El formato del correo no es válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Solo si todo es válido: registrar y limpiar
            val nuevoAlumno = Alumno(codigo, apellidos, nombres, correo)
            listaAlumnos.add(nuevoAlumno)
            Toast.makeText(this, "Alumno registrado correctamente", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        }

        btnVerLista.setOnClickListener {
            startActivity(Intent(this, ListaAlumnosActivity::class.java))
        }
    }

    private fun limpiarCampos() {
        edtCodigo.text.clear()
        edtApellidos.text.clear()
        edtNombres.text.clear()
        edtCorreo.text.clear()
    }
}
