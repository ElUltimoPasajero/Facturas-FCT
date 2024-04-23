package com.example.facturas_tfc.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivitySmartSolarBinding
import com.google.android.material.appbar.MaterialToolbar

class SmartSolarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartSolarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_smart_solar)

        binding = ActivitySmartSolarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: MaterialToolbar = binding.materialToolbarSmartSolar


        toolbar.setNavigationOnClickListener {
            MainActivity.create(this)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_smart_solar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}



