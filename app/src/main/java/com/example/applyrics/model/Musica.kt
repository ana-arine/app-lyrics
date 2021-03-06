package com.example.applyrics.model

import com.google.gson.annotations.SerializedName

class Musica(
    @SerializedName("nomeArtista")val nomeArtista: String,
    @SerializedName("nomeMusica")val nomeMusica: String,
    @SerializedName("letra")val letra: String,
)
