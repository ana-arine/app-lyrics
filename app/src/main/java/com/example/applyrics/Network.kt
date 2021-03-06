package com.example.applyrics

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    companion object{
        fun RetrofitConfig(caminho: String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(caminho)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}