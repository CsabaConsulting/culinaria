package com.sena.appculinariavirtual.adapters

import android.content.Context
import android.widget.ArrayAdapter

// Adaptador de los Spinners

class ArrayAdapterSpinner(context: Context, resource: Int = android.R.layout.simple_spinner_item, items: List<String>) {

    private var adapter: ArrayAdapter<*> = ArrayAdapter(context, resource, items)

    init {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    fun getAdapter(): ArrayAdapter<*> = adapter

}