package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.network.FirebaseAuthService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton

class SignUpUseCase @Inject constructor(private val firebaseAuthService: FirebaseAuthService) {

    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {

        firebaseAuthService.signUp(email, password) { isSuccess ->
            callback(isSuccess)
        }
    }

     fun isPasswordValid(password: String): Boolean {
        val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$".toRegex()
        return regex.matches(password)
    }
}
