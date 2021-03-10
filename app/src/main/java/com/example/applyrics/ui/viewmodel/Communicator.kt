package com.example.applyrics.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel() {

    val mensagem = MutableLiveData<MutableList<String>>()

    fun msgCommunicator (lista: MutableList<String>) {
        mensagem.value = lista
    }
}