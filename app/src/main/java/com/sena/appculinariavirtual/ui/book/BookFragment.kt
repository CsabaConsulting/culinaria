package com.sena.appculinariavirtual.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sena.appculinariavirtual.R
import kotlinx.android.synthetic.main.fragment_book.view.*

class BookFragment: Fragment() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_book, container, false)

        bookViewModel.text.observe(viewLifecycleOwner, Observer {
            root.content.text = it
        })

        return root
    }

}