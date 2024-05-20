package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.network.FirebaseAuthService


class SignInUseCase(private val authService: FirebaseAuthService) {

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        val taskMock = authService.signIn(email, password)

        taskMock.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true)
            } else {
                callback(false)
            }
        }
    }
}