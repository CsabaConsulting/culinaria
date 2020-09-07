package com.sena.appculinariavirtual.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class BookViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Esto es un mensaje en tiempo real"
    }

    val text: LiveData<String> = _text
}