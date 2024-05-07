package com.example.facturas_tfc.ui.activities

import AuthenticatorandLoginViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthenticatorandLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.initSharedPreferences(this)
        viewModel.loadSavedCredentials()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_60dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }

        initButtonEnter()
        initButtonRegister()
        initButtonForgotPassword()

        if (intent.getBooleanExtra("logout", true)) {
            handleAutoLogin()
        }
    }

    private fun handleAutoLogin() {
        val (savedEmail, savedPassword) = viewModel.loadSavedCredentials()
        if (savedEmail != null && savedPassword != null &&
            savedEmail.isNotEmpty() && savedPassword.isNotEmpty()
        ) {
            binding.userEditText.setText(savedEmail)
            binding.editTextPasword.setText(savedPassword)

            viewModel.signIn(savedEmail, savedPassword) { isSuccess ->
                if (isSuccess) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initButtonForgotPassword() {
        binding.buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initButtonRegister() {
        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initButtonEnter() {
        binding.buttonEnter.setOnClickListener {
            val email = binding.userEditText.text.toString().trim()
            val password = binding.editTextPasword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Por favor, introduce tu correo electrónico y contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.signIn(email, password) { isSuccess ->
                    if (isSuccess) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (binding.checkBoxRememberPassword.isChecked) {
                        viewModel.saveCredentials(email, password)
                    } else {
                        viewModel.clearCredentials()
                    }
                }
            }
        }
    }
}