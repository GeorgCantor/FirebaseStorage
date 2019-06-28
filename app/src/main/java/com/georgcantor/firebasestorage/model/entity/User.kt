package com.georgcantor.firebasestorage.model.entity

data class User(
        var username: String? = null,
        var password: String? = null,
        var confirmPassword: String? = null,
        var email: String? = null,
        var userId: String? = null
)