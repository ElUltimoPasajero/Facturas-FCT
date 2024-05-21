package com.example.facturas_tfc.ui.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivitySingUpBinding
import com.example.facturas_tfc.viewmodel.AuthenticatorandLoginViewModel

class SingUpActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthenticatorandLoginViewModel
    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this).get(AuthenticatorandLoginViewModel::class.java)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        initSignUpButton()
        initGoToLoginButton()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_60dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }
    }

    private fun initGoToLoginButton() {
        binding.signupButtonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$".toRegex()
        return regex.matches(password)
    }

    private fun initSignUpButton() {
        binding.buttonEnter.setOnClickListener {
            val email = binding.textInputUser.text.toString()
            val password = binding.textInputPassword.text.toString()
            val repeatPassword = binding.textInputRepeatPassword.text.toString()

            if (password == repeatPassword) {
                if (authViewModel.isPasswordValid(password)) {
                    authViewModel.signUp(email, password) { isSuccess ->
                        if (isSuccess) {
                            Toast.makeText(this, "Usuario Creado Correctamente", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Error al registrar el usuario",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un número",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }


}