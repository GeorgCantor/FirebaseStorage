package com.georgcantor.firebasestorage.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.transition.TransitionManager

class EditTextWatcher(private val button: Button) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(charSequence: CharSequence?,
                                   start: Int,
                                   count: Int,
                                   after: Int) {
        TransitionManager.beginDelayedTransition(button.rootView as ViewGroup)
        if (charSequence.isNullOrEmpty()) {
            button.visibility = View.GONE
        } else {
            button.visibility = View.VISIBLE
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}