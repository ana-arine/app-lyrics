package com.example.applyrics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel() {

    val mensagem = MutableLiveData<MutableList<String>>()
    val mensagem2 = MutableLiveData<Any?>()

    fun msgCommunicator (lista: MutableList<String>) {
        mensagem.value = lista
    }
}