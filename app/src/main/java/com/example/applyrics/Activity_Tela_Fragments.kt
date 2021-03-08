package com.example.applyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.applyrics.model.Musica
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Activity_Tela_Fragments : AppCompatActivity() {

    class BuscarFragment : Fragment() {

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
//                    val bundle = Bundle()
//                    bundle.putString("BANDA", nomebanda)
//                    bundle.putString("MUSICA", nomemusica)
//                    val destino = LetraFragment()
//                    destino.arguments = bundle

                    // compartilhar dados com a próxima fragment
//                    model!!.msgCommunicator(nomemusica) // dp Communicator
//                    model!!.msgCommunicator(nomebanda) // dp Communicator
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

    class LetraFragment : Fragment() {

//        private var bandaRecebida = "Coldplay"
//        private var musicaRecebida = "Yellow"

        private lateinit var letramusica: TextView
        //private lateinit var letramusica2 : TextView  // teste

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_letra, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            letramusica = view.findViewById(R.id.txtLetra)
//            letramusica2 = view.findViewById(R.id.txtLetra2) // teste

//            val bandarecebida = arguments?.getString("BANDA").toString()
//            val musicarecebida = arguments?.getString("MUSICA").toString()
//            val bandaRecebida = getArguments()?.getString("BANDA")
//            val musicaRecebida = getArguments()?.getString("MUSICA")

//            if (bandaRecebida != null && musicaRecebida != null) {
//                buscarMusica(bandaRecebida, musicaRecebida)
//            }
//
//                 var bandaRecebida = "Coldplay"
//                 var musicaRecebida = "Yellow"

            //bandaRecebida = view.findViewById(R.id.edtBanda)
            //musicaRecebida = view.findViewById(R.id.edtBanda)


            // pegar dados do Communicator
            val model = ViewModelProviders.of(activity!!).get(Communicator::class.java) // dp Communicator

//            model.mensagem.observe(this, { o ->      // dp Communicator
//                bandaRecebida = o.toString()
//                musicaRecebida = o.toString()
//            buscarMusica(bandaRecebida, musicaRecebida)
//            })

//            model.mensagem.observe(this, object : Observer<Any?> {  // dp Communicator
//                override fun onChanged(o: Any?) {
//                    letramusica.text = o.toString()
//                    letramusica2.text = o.toString()
//                }
//            })

//            model.mensagem.observe(this, object : Observer<Any?> {  // dp Communicator
//                override fun onChanged(o: Any?) {
//                    bandaRecebida = o.toString()
//                    musicaRecebida = o.toString()
//                    buscarMusica(bandaRecebida, musicaRecebida)
//                }
//            })
//        }

//            model.mensagem.observe(this, object : Observer<Any?> {  // dp Communicator
//                override fun onChanged(o: Any?) {
//                    bandaRecebida = o.toString()
//                }
//            })
//            model.mensagem2.observe(this, object : Observer<Any?> {  // dp Communicator
//                override fun onChanged(o: Any?) {
//                    musicaRecebida = o.toString()
//                }
//            })
//            buscarMusica(bandaRecebida, musicaRecebida)

            model.mensagem.observe(this, object : Observer<MutableList<String>> {  // dp Communicator

                override fun onChanged(t: MutableList<String>) {
                    var bandaRecebida: String
                    var musicaRecebida: String
                    bandaRecebida = t[0]
                    musicaRecebida = t[1]
                    buscarMusica(bandaRecebida,musicaRecebida)
                }
            })
        }

        fun buscarMusica(nomebanda: String, nomemusica: String) {

            val retrofitClient = Network.RetrofitConfig("https://api.lyrics.ovh/v1/")
            val servico = retrofitClient.create(MusicaService::class.java)
            val chamada = servico.buscarMusica(nomebanda, nomemusica)

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


