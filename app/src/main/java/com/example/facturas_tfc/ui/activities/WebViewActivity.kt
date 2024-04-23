package com.example.facturas_tfc.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivitySmartSolarBinding
import com.example.facturas_tfc.databinding.ActivityWebViewBinding
import com.google.android.material.appbar.MaterialToolbar

class WebViewActivity : AppCompatActivity() {

    private  lateinit var binding : ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: MaterialToolbar = binding.includeMaterialToolbarWebView.materialToolbar


        toolbar.setNavigationOnClickListener {
            startActivity(MainActivity.create(this))
        }
    }
}