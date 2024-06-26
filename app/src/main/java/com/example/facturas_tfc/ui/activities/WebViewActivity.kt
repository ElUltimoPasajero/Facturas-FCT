package com.example.facturas_tfc.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.facturas_tfc.databinding.ActivityWebViewBinding
import com.google.android.material.appbar.MaterialToolbar

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = binding.includeMaterialToolbarWebView.materialToolbar

        toolbar.setNavigationOnClickListener {
            startActivity(MainActivity.create(this))
        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            insets
        }

        initWebViewButtons()

    }

    private fun initWebViewButtons() {
        binding.buttonOpenNavegator.setOnClickListener {
            binding.webView.loadUrl("https://www.iberdrola.es")
        }

        binding.buttonOpenNavView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iberdrola.es"))
            startActivity(intent)
        }
    }
}