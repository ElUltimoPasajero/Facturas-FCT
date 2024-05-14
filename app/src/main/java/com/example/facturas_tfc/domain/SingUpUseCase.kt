package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.network.FirebaseAuthService

class SignUpUseCase(private val firebaseAuthService: FirebaseAuthService) {

    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {

        firebaseAuthService.signUp(email, password) { isSuccess ->
            callback(isSuccess)
        }
    }
}