package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.network.FirebaseAuthService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignInUseCase @Inject constructor(private val authService: FirebaseAuthService) {

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