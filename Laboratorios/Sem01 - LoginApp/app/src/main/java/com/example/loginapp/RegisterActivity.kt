package com.example.loginapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var edtNewUser: EditText
    private lateinit var edtNewPass: EditText
    private lateinit var btnRegister: Button
    private lateinit var txtGoLogin: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Oculta la barra superior
        setContentView(R.layout.activity_register)

        // Enlazar vistas
        edtNewUser = findViewById(R.id.edtNewUser)
        edtNewPass = findViewById(R.id.edtNewPass)
        btnRegister = findViewById(R.id.btnRegister)
        txtGoLogin = findViewById(R.id.txtGoLogin)
        sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Botón para registrar usuario
        btnRegister.setOnClickListener {
            val newUser = edtNewUser.text.toString()
            val newPass = edtNewPass.text.toString()

            if (newUser.isEmpty() || newPass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            with(sharedPref.edit()) {
                putString("username", newUser)
                putString("password", newPass)
                apply()
            }

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Texto para volver al login
        txtGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
