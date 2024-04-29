package com.example.facturas_tfc.ui.activities

import AuthenticatorViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivitySingUpBinding

class SingUpActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthenticatorViewModel
    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this).get(AuthenticatorViewModel::class.java)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        initSignUpButton()
        initGoToLoginButton()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




    }

    private fun initGoToLoginButton() {
        binding.signupButtonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSignUpButton() {
        binding.buttonEnter.setOnClickListener {
            val email = binding.editTextUser.text.toString()
            val password = binding.editTextPassword.text.toString()
            val repeatPassword = binding.editTextRepeatPassword.text.toString()

            if (password == repeatPassword) {
                authViewModel.signUp(email, password) { isSuccess ->
                    if (isSuccess) {
                        Toast.makeText(this, "Usuario Creado Correctamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }



}