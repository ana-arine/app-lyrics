package com.example.applyrics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.applyrics.model.Musica
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Activity_Tela_Fragments : AppCompatActivity() {


    private lateinit var banda: EditText
    private lateinit var musica : EditText
    private lateinit var botao : Button
    private lateinit var letramusica : TextView
    private lateinit var letra : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__tela__fragments)
        supportActionBar?.hide()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)


            banda = findViewById(R.id.edtBanda)
            botao = findViewById(R.id.btn)
            musica = findViewById(R.id.edtMusica)
            //letramusica = findViewById(R.id.txtMusica)


            botao.setOnClickListener {
                val nomebanda = banda.text.toString()
                val nomemusica = musica.text.toString()
                if(nomebanda.isNotEmpty() && nomemusica.isNotEmpty()){
                    buscarMusica(nomebanda, nomemusica)
                    val destinoActivity = Intent(this, LetraFragment::class.java)
                    destinoActivity.putExtra("LETRA", letra)
                    startActivity(destinoActivity)

                } else {
                    banda.error = "Errrrrrou!"
                }
            }

        }

        fun buscarMusica(nomebanda: String, nomemusica: String){

            val retrofitClient = Network.RetrofitConfig("https://api.lyrics.ovh/v1/")


            val servico = retrofitClient.create(MusicaService::class.java)

            val chamada = servico.buscarMusica(nomebanda,nomemusica)

            chamada.enqueue(
                object : Callback<Musica> {

                    override fun onResponse(call: Call<Musica>, response: Response<Musica>) {
                         letra = response.body()?.letra.toString()
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

