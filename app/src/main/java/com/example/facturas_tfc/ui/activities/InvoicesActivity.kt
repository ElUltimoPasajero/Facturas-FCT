package com.example.facturas_tfc.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.facturas_tfc.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InvoicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_invoices)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.padding_20dp)
            v.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels)
            insets
        }
    }
}