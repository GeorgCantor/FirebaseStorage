package com.georgcantor.firebasestorage.utils

import java.text.SimpleDateFormat
import java.util.*

object Constant {

    const val USER_NAME = "user_name"
    const val USER_ID = "user_id"
    const val USER_EMAIL = "user_email"

    val time: String
        get() = SimpleDateFormat("dd MMM yyyy , HH.mm", Locale.getDefault()).format(Date())
}