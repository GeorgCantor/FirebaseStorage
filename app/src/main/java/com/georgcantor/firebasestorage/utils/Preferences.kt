package com.georgcantor.firebasestorage.utils

import android.content.Context
import android.content.SharedPreferences
import com.georgcantor.firebasestorage.model.entity.User

class Preferences {

    companion object {
        private lateinit var preferences: SharedPreferences

        fun initReferences(context: Context): Preferences {
            preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)

            return Preferences()
        }
    }

    var user: User
        get() {
            val user = User()
            user.username = preferences.getString(Constant.USER_NAME, "")
            user.email = preferences.getString(Constant.USER_EMAIL, "")
            user.userId = preferences.getString(Constant.USER_ID, "")

            return user
        }
        set(it) {
            val editor = preferences.edit()
            editor.putString(Constant.USER_NAME, it.username)
            editor.putString(Constant.USER_EMAIL, it.email)
            editor.putString(Constant.USER_ID, it.userId)
            editor.apply()
        }
}