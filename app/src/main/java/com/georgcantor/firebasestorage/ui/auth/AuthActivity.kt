package com.georgcantor.firebasestorage.ui.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.georgcantor.firebasestorage.R
import com.georgcantor.firebasestorage.model.entity.User

class AuthActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            replaceFragment
        }
    }

    override fun onLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRegister() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAuthSuccess(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
