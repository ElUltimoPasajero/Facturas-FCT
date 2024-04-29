package com.example.facturas_tfc.ui.activities

import AuthenticatorViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthenticatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this).get(AuthenticatorViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_40dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }

        binding.buttonEnter.setOnClickListener {
            val email = binding.editTextUser.text.toString()
            val password = binding.editTextPassword.text.toString()

            authViewModel.signIn(email, password) { isSuccess ->
                if (isSuccess) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}