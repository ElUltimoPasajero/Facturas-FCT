package com.example.facturas_tfc.data.network

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthService {
    private var instance: FirebaseAuth? = null

    fun getInstance(): FirebaseAuth {
        return instance ?: synchronized(this) {
            val newInstance = FirebaseAuth.getInstance()
            instance = newInstance
            newInstance
        }
    }

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        val firebaseAuth = getInstance()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
    }

    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {
        val firebaseAuth = getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
    }

}