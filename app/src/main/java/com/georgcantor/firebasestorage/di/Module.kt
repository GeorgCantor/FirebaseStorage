package com.georgcantor.firebasestorage.di

import com.georgcantor.firebasestorage.model.repo.References
import com.georgcantor.firebasestorage.model.repo.auth.AuthRepo
import com.georgcantor.firebasestorage.model.repo.db.MessageRepo
import com.google.firebase.auth.FirebaseAuth

val module = org.koin.dsl.module {

    single { References() }
    single { FirebaseAuth.getInstance() }

    factory { AuthRepo(get(), get()) }
    factory { MessageRepo(get()) }
}