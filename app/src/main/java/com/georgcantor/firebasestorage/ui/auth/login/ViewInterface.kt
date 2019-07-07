package com.georgcantor.firebasestorage.ui.auth.login

import com.georgcantor.firebasestorage.model.entity.User

interface ViewInterface {

    fun onEmailEmpty()

    fun onEmailInvalid()

    fun onPasswordEmpty()

    fun onLoginStart()

    fun onProgress(visibility: Int)

    fun onLoginSuccess(user: User)

    fun onLoginFailed(error: String?)
}