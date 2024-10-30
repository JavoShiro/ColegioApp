package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btnClass1: Button
    private lateinit var btnClass2: Button
    private lateinit var btnClass3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClass1 = findViewById(R.id.btclass1)
        btnClass2 = findViewById(R.id.btclass2)
        btnClass3 = findViewById(R.id.btclass3)

        btnClass1.setOnClickListener {
            navigateToLogin()
        }
        btnClass2.setOnClickListener {
            navigateToLogin()
        }
        btnClass3.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
            val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

