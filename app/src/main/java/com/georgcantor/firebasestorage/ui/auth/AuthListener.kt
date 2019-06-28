package com.georgcantor.firebasestorage.ui.auth

import com.georgcantor.firebasestorage.model.entity.User

interface AuthListener {

    fun onLogin()

    fun onRegister()

    fun onAuthSuccess(user: User)
}