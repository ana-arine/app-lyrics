package com.example.applyrics.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.applyrics.ui.viewmodel.Communicator
import com.example.applyrics.network.MusicaServiceInterface
import com.example.applyrics.network.Network
import com.example.applyrics.R
import com.example.applyrics.model.Musica
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LetraMusicasFragment : Fragment() {

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
        val servico = retrofitClient.create(MusicaServiceInterface::class.java)
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