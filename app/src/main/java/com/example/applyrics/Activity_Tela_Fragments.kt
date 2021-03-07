package com.example.applyrics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.applyrics.model.Musica
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tela_fragments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Activity_Tela_Fragments : AppCompatActivity() {

    class BuscarFragment : Fragment() {


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            return inflater.inflate(R.layout.fragment_buscar, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            var banda: EditText = view.findViewById(R.id.edtBanda)
            var musica: EditText = view.findViewById(R.id.edtMusica)
            var botao: Button = view.findViewById(R.id.btn)


            botao.setOnClickListener {
                val nomebanda = banda.text.toString()
                val nomemusica = musica.text.toString()
                if(nomebanda.isNotEmpty() && nomemusica.isNotEmpty()){
                    val bundle = Bundle()
                    bundle.putString("BANDA", nomebanda)
                    bundle.putString("MUSICA", nomemusica)
                    val destino = ListaFragment()
                    destino.arguments = bundle
                } else {
                    banda.error = "Errrrrrou!"
                }
            }

        }

    }

    class LetraFragment : Fragment() {
        private lateinit var letramusica : TextView
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            return inflater.inflate(R.layout.fragment_letra, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

//                val bandarecebida = arguments?.getString("BANDA").toString()
//                val musicarecebida = arguments?.getString("MUSICA").toString()
                val bandarecebida = "Coldplay"
                val musicarecebida = "Yellow"


                buscarMusica(bandarecebida, musicarecebida)
                letramusica = view.findViewById(R.id.txtLetra)


        }

        fun buscarMusica(nomebanda: String, nomemusica: String){
            val retrofitClient = Network.RetrofitConfig("https://api.lyrics.ovh/v1/")

            val servico = retrofitClient.create(MusicaService::class.java)

            val chamada = servico.buscarMusica(nomebanda,nomemusica)

            chamada.enqueue(
                object : Callback<Musica> {
                    //Comunicação com a API OK!
                    override fun onResponse(call: Call<Musica>, response: Response<Musica>) {
                        val letra = response.body()?.letra
                        letra?.let {
                            if (it.isNotEmpty()) {
                                letramusica.text = it
                            } else {
                                letramusica.error = "Opa... Não tem Letra!"
                            }
                        }
                    }
                    override fun onFailure(call: Call<Musica>, t: Throwable) {
                        letramusica.text = "Opa... Houve erro na solicitação." +
                                "\nTente novamente mais tarde."
                    }
                }
            )


        }
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_fragments)
        supportActionBar?.hide()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}


