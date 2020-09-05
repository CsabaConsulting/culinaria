package com.sena.appculinariavirtual.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sena.appculinariavirtual.MainActivity
import com.sena.appculinariavirtual.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener { loginUser() }
        btnCreateAccount.setOnClickListener { goToCreateAccount() }
    }

    private fun loginUser(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToCreateAccount(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
    
}