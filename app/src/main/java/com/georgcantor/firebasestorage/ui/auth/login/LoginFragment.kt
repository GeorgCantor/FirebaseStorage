package com.georgcantor.firebasestorage.ui.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.georgcantor.firebasestorage.R
import com.georgcantor.firebasestorage.hideKeyboard
import com.georgcantor.firebasestorage.model.entity.User
import com.georgcantor.firebasestorage.showSnackbar
import com.georgcantor.firebasestorage.ui.auth.AuthListener
import com.georgcantor.firebasestorage.utils.InputTextWatcher
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : Fragment(), ViewInterface {

    private lateinit var authListener: AuthListener
    private val presenter by inject<Presenter>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        emailEditText.addTextChangedListener(InputTextWatcher(emailView))
        passwordEditText.addTextChangedListener(InputTextWatcher(passwordView))

        signUpTextView.setOnClickListener { authListener.onRegister() }
        buttonSignIn.setOnClickListener {
            val user = User(
                    password = passwordEditText.text.toString().trim(),
                    email = emailEditText.text.toString().trim()
            )
            presenter.doLogin(user)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        authListener = context as AuthListener
    }

    override fun onEmailEmpty() {
        emailView.error = "Введите email"
        emailEditText.requestFocus()
    }

    override fun onEmailInvalid() {
        emailView.error = "Неверный формат"
        emailEditText.requestFocus()
    }

    override fun onPasswordEmpty() {
        passwordView.error = "Введите пароль"
        passwordEditText.requestFocus()
    }

    override fun onLoginStart() {
        requireContext().hideKeyboard(emailEditText)
        buttonSignIn.isEnabled = false
        isEditTextEnabled(false)
    }

    override fun onProgress(visibility: Int) {
        progressView.visibility = visibility
    }

    override fun onLoginSuccess(user: User) {
        authListener.onAuthSuccess(user)
    }

    override fun onLoginFailed(error: String?) {
        error?.let { rootView.showSnackbar(it) }
        buttonSignIn.isEnabled = true
        isEditTextEnabled(true)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    private fun isEditTextEnabled(status: Boolean) {
        emailEditText.isEnabled = status
        passwordEditText.isEnabled = status
    }
}