package com.georgcantor.firebasestorage.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.georgcantor.firebasestorage.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth != null) {

        } else {

        }

        finish()
    }
}
