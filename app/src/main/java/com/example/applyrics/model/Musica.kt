package com.example.applyrics.model

import com.google.gson.annotations.SerializedName

class Musica(
    val nomeArtista: String,
    val nomeMusica: String,
    @SerializedName("lyrics")val letra: String,
)
