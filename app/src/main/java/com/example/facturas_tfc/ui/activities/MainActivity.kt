package com.example.facturas_tfc.ui.activities

import AuthenticatorandLoginViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var practiceList: List<PracticeVO>
    private lateinit var adapter: PracticeAdapter
    private val viewModel: InvoiceActivityViewmodel by viewModels()
    private val authViewModel: AuthenticatorandLoginViewModel by viewModels()


    companion object {
        fun create(context: Context): Intent {


            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return intent

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        authViewModel.initSharedPreferences(this)
        practiceList = (listOf(
            PracticeVO(1, "Práctica 1"),
            PracticeVO(2, "Práctica 2"),
            PracticeVO(3, "Vista de Navegación")
        ))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PracticeAdapter(practiceList) { practice ->
            onItemSelected(practice)

        }
        binding.rvSelectProject.adapter = adapter
        binding.rvSelectProject.layoutManager = LinearLayoutManager(this)

        val decoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.rvSelectProject.addItemDecoration(decoration)





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupLogoutButton()

    }

    private fun onItemSelected(practice: PracticeVO) {


        if (practice.id == 1) {
            val intent = Intent(this, InvoicesActivity::class.java)
            startActivity(intent)

        } else {


            if (practice.id == 2) {
                val intent = Intent(this, SmartSolarActivity::class.java)
                startActivity(intent)


            } else {

                if (practice.id == 3) {
                    val intent = Intent(this, WebViewActivity::class.java)
                    startActivity(intent)


                }
            }
        }
    }
    private fun setupLogoutButton() {
        val logoutButton = findViewById<Button>(R.id.buttonLogout)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            authViewModel.clearCredentials()
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("logout", false)
            startActivity(intent)
            finish()
        }
    }

}