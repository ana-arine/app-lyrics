package com.example.applyrics

import com.example.applyrics.model.Musica
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicaService {
    @GET("{banda}/{musica}/")

    fun buscarMusica(

        @Path("banda") banda: String,
        @Path("musica") musica: String
    ) : Call<Musica>
}
