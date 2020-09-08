package com.sena.appculinariavirtual.ui.book

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.sena.appculinariavirtual.R
import com.sena.appculinariavirtual.ui.scanner.ScannerActivity
import kotlinx.android.synthetic.main.fragment_book.view.*

class BookFragment: Fragment(), PermissionListener {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var fragmentContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_book, container, false)
        fragmentContext = root.context

        bookViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        root.btnScanQr.setOnClickListener {
            Dexter.withContext(fragmentContext)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(this)
                .check()
        }

        return root
    }

    private fun goToScanQr(){
        val intent = Intent(fragmentContext, ScannerActivity::class.java)
        startActivity(intent)
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        goToScanQr()
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }

}