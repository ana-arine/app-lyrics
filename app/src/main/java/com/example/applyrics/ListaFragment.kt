package com.example.applyrics


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applyrics.model.Musica

class ListaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listaMusicas = mutableListOf(
            Musica("A", "A", "A"),
            Musica("B", "B", "B"),
            Musica("C", "C", "C"),
            Musica("D", "D", "D")

        )

        val rvMusicas = view.findViewById<RecyclerView>(R.id.rvLista)

        val adapterMusicas = ListaMusicasAdapter(listaMusicas)
        rvMusicas.adapter = adapterMusicas

        rvMusicas.layoutManager = LinearLayoutManager(context)


    }
}