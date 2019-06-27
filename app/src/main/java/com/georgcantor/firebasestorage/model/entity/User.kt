package com.georgcantor.firebasestorage.model.entity

data class User(
        var username: String?,
        var password: String?,
        var confirmPassword: String?,
        var email: String?,
        var userId: String?
)