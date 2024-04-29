package com.example.facturas_tfc.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivityForgotPaswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPaswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_forgot_pasword)
        binding = ActivityForgotPaswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_40dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }

        binding.backToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}