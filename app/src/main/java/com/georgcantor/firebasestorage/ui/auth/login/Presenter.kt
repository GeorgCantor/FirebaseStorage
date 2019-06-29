package com.georgcantor.firebasestorage.ui.auth.login

import com.georgcantor.firebasestorage.isEmailValid
import com.georgcantor.firebasestorage.model.entity.User
import com.georgcantor.firebasestorage.model.repo.auth.AuthRepo

class Presenter(authRepo: AuthRepo) {

    private var loginView: LoginView? = null

    fun attachView(view: LoginView) {
        loginView = view
    }

    fun detachView() {
        loginView = null
    }

    fun doLogin(user: User) {
        if (user.email?.isEmpty() == true) {
            loginView?.onEmailEmpty()

            return
        }

        if (user.email?.isEmailValid() == false) {
            loginView?.onEmailInvalid()

            return
        }

        if (user.password?.isEmpty() == true) {
            loginView?.onPasswordEmpty()

            return
        }
    }
}