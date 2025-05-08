package com.example.registroalumnos

data class Alumno(
    val codigo: String,
    val apellidos: String,
    val nombres: String,
    val correo: String
) {
    fun nombreCompleto(): String = "$apellidos, $nombres"
}
