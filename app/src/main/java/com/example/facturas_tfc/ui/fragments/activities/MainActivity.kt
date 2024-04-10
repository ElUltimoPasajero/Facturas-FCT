package com.example.facturas_tfc.ui.fragments.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.adapter.PracticeAdapter
import com.example.facturas_tfc.databinding.ActivityMainBinding
import com.example.facturas_tfc.entities.PracticeVO
import com.example.facturas_tfc.viewmodel.InvoiceActivityViewmodel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var practiceList: List<PracticeVO>
    private lateinit var adapter: PracticeAdapter
    private val viewModel: InvoiceActivityViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        practiceList = (listOf(PracticeVO(1, "Práctica 1"), PracticeVO(2, "Práctica 2")))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getInvoices()
        adapter = PracticeAdapter(practiceList) { practice ->
            onItemSelected(practice)

        }
        binding.rvSelectProject.adapter = adapter
        binding.rvSelectProject.layoutManager = LinearLayoutManager(this)

        val decoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.rvSelectProject.addItemDecoration(decoration)


        viewModel.getInvoices().observe(this) { invoices ->
            // Aquí puedes hacer lo que quieras con la lista, por ejemplo, pasársela al adaptador
            // o imprimir en el registro (log)
            Log.d("FACTURAS", invoices.toString())
            //adapter.submitList(invoices)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onItemSelected(practice: PracticeVO) {

        if (practice.id == 1) {
            val intent = Intent(this, InvoicesActivity::class.java)
            startActivity(intent)

        }
    }
}