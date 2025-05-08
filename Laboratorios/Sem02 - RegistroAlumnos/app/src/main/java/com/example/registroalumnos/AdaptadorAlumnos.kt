package com.example.registroalumnos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorAlumnos(private var lista: List<Alumno>) :
    RecyclerView.Adapter<AdaptadorAlumnos.AlumnoViewHolder>() {

    class AlumnoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCorreo: TextView = view.findViewById(R.id.tvCorreo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno, parent, false)
        return AlumnoViewHolder(vista)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumno = lista[position]
        holder.tvCodigo.text = "🆔 ${alumno.codigo}"
        holder.tvNombre.text = alumno.nombreCompleto()
        holder.tvCorreo.text = "📧 ${alumno.correo}"
    }

    fun actualizarLista(nuevaLista: List<Alumno>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}
