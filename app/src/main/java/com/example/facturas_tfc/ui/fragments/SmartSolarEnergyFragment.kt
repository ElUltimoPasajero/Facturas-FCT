package com.example.facturas_tfc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.FragmentSmartSolarEnergyBinding
import com.example.facturas_tfc.viewmodel.SmartSolarEnergyActivityViewModel


class SmartSolarEnergyFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_smart_solar_energy, container, false)
    }




    }


