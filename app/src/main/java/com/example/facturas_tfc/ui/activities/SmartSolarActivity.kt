package com.example.facturas_tfc.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.ActivitySmartSolarBinding
import com.example.facturas_tfc.ui.fragments.SmartSolarDataFragment
import com.example.facturas_tfc.ui.fragments.SmartSolarEnergyFragment
import com.example.facturas_tfc.ui.fragments.SmartSolarInstallationFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class SmartSolarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartSolarBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var fragmentContainer: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySmartSolarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.includeMaterialToolbarSmartSolar.materialToolbar
        tabLayout = findViewById(R.id.tablayout)
        fragmentContainer = findViewById(R.id.fragment_container)

        setSupportActionBar(toolbar)
        replaceFragment(SmartSolarInstallationFragment())



        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {

                    0 -> replaceFragment(SmartSolarInstallationFragment())
                    1 -> replaceFragment(SmartSolarEnergyFragment())
                    2 -> replaceFragment(SmartSolarDataFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        toolbar.setNavigationOnClickListener {
            startActivity(MainActivity.create(this))
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.activitySmartSolar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

