package com.georgcantor.firebasestorage.model.repo

import com.google.firebase.database.FirebaseDatabase

class References {

    fun chatRefs() = FirebaseDatabase.getInstance().getReference("chat")

    fun userRefs() = FirebaseDatabase.getInstance().getReference("user")
}