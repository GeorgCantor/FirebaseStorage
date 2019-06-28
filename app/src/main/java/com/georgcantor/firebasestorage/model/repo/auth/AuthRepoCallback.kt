package com.georgcantor.firebasestorage.model.repo.auth

import com.georgcantor.firebasestorage.model.entity.User

interface AuthRepoCallback {

    fun onSuccess(user: User)

    fun onFailed(error: String?)
}