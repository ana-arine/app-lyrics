package com.example.applyrics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel() {

    val mensagem = MutableLiveData<Any?>()
    val mensagem2 = MutableLiveData<Any?>()

    fun msgCommunicator (nomebanda: String, nomemusica: String) {
        mensagem.value = nomebanda
        mensagem2.value = nomemusica
    }
}