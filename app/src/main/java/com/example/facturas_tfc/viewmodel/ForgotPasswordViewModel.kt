package com.example.facturas_tfc.viewmodel

import ForgotPasswordUseCase
import androidx.lifecycle.ViewModel


class ForgotPasswordViewModel : ViewModel() {

    private val forgotPasswordUseCase = ForgotPasswordUseCase()

    fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit) {
        forgotPasswordUseCase.sendPasswordResetEmail(email) { isSuccess ->
            callback(isSuccess)
        }
    }
}