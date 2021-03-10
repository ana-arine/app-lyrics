package com.example.applyrics.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.applyrics.ui.viewmodel.Communicator
import com.example.applyrics.R

class BuscarMusicasFragment : Fragment() {

    private var model: Communicator? = null  // dp Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(Communicator::class.java)  // dp Communicator


        val banda: EditText = view.findViewById(R.id.edtBanda)
        val musica: EditText = view.findViewById(R.id.edtMusica)
        val botao: Button = view.findViewById(R.id.btn)


        botao.setOnClickListener {
            val nomebanda = banda.text.toString()
            val nomemusica = musica.text.toString()
            if (nomebanda.isNotEmpty() && nomemusica.isNotEmpty()) {
                val bandaemusica: MutableList<String> = mutableListOf()
                bandaemusica.add(nomebanda)
                bandaemusica.add(nomemusica)
                banda.setText("")
                musica.setText("")
                banda.requestFocus()

                // compartilhar dados com a próxima fragment
                model!!.msgCommunicator(bandaemusica) // dp Communicator

                // iniciar a tab e fragment "Letra"
                val proximaTab = requireActivity().findViewById<ViewPager>(R.id.viewPager)
                proximaTab.currentItem = 1
            } else {
                banda.error = "Errrrrrou!"
            }
        }
    }
}