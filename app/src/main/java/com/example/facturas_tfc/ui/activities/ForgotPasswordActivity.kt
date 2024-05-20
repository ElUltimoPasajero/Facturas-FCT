package com.example.facturas_tfc.ui.activities

import ForgotPasswordViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivityForgotPaswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPaswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_forgot_pasword)
        binding = ActivityForgotPaswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBackToLogin()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_60dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }


        binding.btnSend.setOnClickListener {
            val email = binding.inputTextForgotPassword.text.toString().trim()

            if (email.isNotEmpty()) {
             viewModel.sendPasswordResetEmail(email){ isSuccess ->
                        if (isSuccess) {
                            Toast.makeText(this, "Se ha enviado un correo electrónico de restablecimiento de contraseña", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error al enviar el correo electrónico de restablecimiento de contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Ingrese su dirección de correo electrónico", Toast.LENGTH_SHORT).show()
            }
        }
        }

    private fun initBackToLogin() {
        binding.backToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()


        }
    }
}