package com.example.applyrics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applyrics.R
import com.example.applyrics.model.Musica


class ListaMusicasAdapter(private val listaMusicas: MutableList<Musica>) : RecyclerView.Adapter<ListaMusicasAdapter.ListaMusicasHolder>(){

    class ListaMusicasHolder(view: View) : RecyclerView.ViewHolder(view){
        val nomeArtista: TextView = view.findViewById(R.id.txtArtista)
        val nomeMusica: TextView = view.findViewById(R.id.txtTituloMusica)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaMusicasHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.itemlista, parent, false)

        return ListaMusicasHolder(view)
    }


    override fun onBindViewHolder(holder: ListaMusicasHolder, position: Int) {
        holder.nomeArtista.text = listaMusicas[position].nomeArtista
        holder.nomeMusica.text = listaMusicas[position].nomeMusica

    }

    override fun getItemCount(): Int = listaMusicas.size

}