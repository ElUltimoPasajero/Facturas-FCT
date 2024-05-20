package com.example.facturas_tfc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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

        super.onViewCreated(view, savedInstanceState)
        binding.smartSolarDetailsInfoButton.setOnClickListener {
            showAlertDialog()
        }


        viewModel.energyDataDetailsLiveData.observe(viewLifecycleOwner) { energyData ->
            binding.editTextCode.setText(energyData.cau)
            binding.textStatus.setText(energyData.requestStatus)
            binding.textAutoConsuptionType.setText(energyData.selfConsumptionType)
            binding.textSurplusesCompensation.setText(energyData.selfConsumptionType)
            binding.textInstallationPower.setText(energyData.installationPower)
        }
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext()).apply {
            setTitle("Estado solicitud autoconsumo")
            setMessage("El tiempo estimado de activación de tu autoconsumo es de 1 a 2 meses, este variará en función de tu comunidad autónoma y distribuidora.")
            setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
        }.create()

        alertDialog.show()
    }

}
