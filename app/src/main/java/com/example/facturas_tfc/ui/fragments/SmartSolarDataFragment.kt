package com.example.facturas_tfc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.FragmentSmartSolarDetailsBinding
import com.example.facturas_tfc.databinding.FragmentSmartSolarEnergyBinding
import com.example.facturas_tfc.viewmodel.SmartSolarEnergyActivityViewModel


class SmartSolarDataFragment : Fragment() {

    private val viewModel: SmartSolarEnergyActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentSmartSolarDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmartSolarDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.energyDataDetailsLiveData.observe(viewLifecycleOwner) { energyData ->
            binding.editTextCode.setText(energyData.cau)
            binding.textStatus.setText(energyData.requestStatus)
            binding.textAutoConsuptionType.setText(energyData.selfConsumptionType)
            binding.textSurplusesCompensation.setText(energyData.selfConsumptionType)
            binding.textInstallationPower.setText(energyData.installationPower)
        }
    }
}
