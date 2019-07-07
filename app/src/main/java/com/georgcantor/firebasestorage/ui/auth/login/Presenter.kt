package com.georgcantor.firebasestorage.ui.auth.login

import android.view.View
import com.georgcantor.firebasestorage.isEmailValid
import com.georgcantor.firebasestorage.model.entity.User
import com.georgcantor.firebasestorage.model.repo.auth.AuthRepo
import com.georgcantor.firebasestorage.model.repo.auth.AuthRepoCallback

class Presenter(private val authRepo: AuthRepo) {

    private var viewInterface: ViewInterface? = null

    fun attachView(view: ViewInterface) {
        viewInterface = view
    }

    fun detachView() {
        viewInterface = null
    }

    fun doLogin(user: User) {
        if (user.email?.isEmpty() == true) {
            viewInterface?.onEmailEmpty()

            return
        }

        if (user.email?.isEmailValid() == false) {
            viewInterface?.onEmailInvalid()

            return
        }

        if (user.password?.isEmpty() == true) {
            viewInterface?.onPasswordEmpty()

            return
        }

        viewInterface?.onLoginStart()
        viewInterface?.onProgress(View.GONE)

        authRepo.doLogin(user, object : AuthRepoCallback {
            override fun onSuccess(user: User) {
                viewInterface?.onLoginSuccess(user)
                viewInterface?.onProgress(View.VISIBLE)
            }

            override fun onFailed(error: String?) {
                viewInterface?.onLoginFailed(error)
                viewInterface?.onProgress(View.VISIBLE)
            }
        })
    }
}