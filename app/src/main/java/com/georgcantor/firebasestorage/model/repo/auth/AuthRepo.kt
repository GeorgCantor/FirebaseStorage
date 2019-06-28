package com.georgcantor.firebasestorage.model.repo.auth

import com.georgcantor.firebasestorage.model.entity.User
import com.georgcantor.firebasestorage.model.repo.References
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AuthRepo(private val authentication: FirebaseAuth,
               private val references: References) {

    fun doRegister(user: User, callback: AuthRepoCallback) {
        authentication
                .createUserWithEmailAndPassword(user.email as String, user.confirmPassword as String)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userId = it.result?.user?.uid
                        val newUser = user.copy(userId = userId)

                        userId?.let { id ->
                            references.userRefs()
                                    .child(id)
                                    .setValue(newUser)

                            callback.onSuccess(newUser)
                        }
                    } else {
                        callback.onFailed(it.exception?.message)
                    }
                }
    }

    fun doLogin(user: User, callback: AuthRepoCallback) {
        authentication
                .signInWithEmailAndPassword(user.email as String, user.password as String)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userId = it.result?.user?.uid

                        userId?.let { id ->
                            references.userRefs()
                                    .child(id)
                                    .addValueEventListener(object : ValueEventListener {
                                        override fun onCancelled(p0: DatabaseError) {
                                        }

                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val newUser = snapshot.getValue(User::class.java) as User
                                            callback.onSuccess(newUser)
                                        }
                                    })
                        }
                    } else {
                        callback.onFailed(it.exception?.message)
                    }
                }
    }
}