package com.example.facturas_tfc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SmartSolarEnergyActivityViewModel : ViewModel(), KoinComponent {

    private val invoiceRepository: InvoiceRepository by inject()

    private val _energyDataDetailLiveData = MutableLiveData<EnergyDataModelRoom>()
    val energyDataDetailsLiveData: LiveData<EnergyDataModelRoom>
        get() = _energyDataDetailLiveData

    init {
        fetchEnergyDataDetails()
        Log.d("KoinTest", "InvoiceRepository instance: $invoiceRepository")

    }


    private fun fetchEnergyDataDetails() {
        viewModelScope.launch {
            invoiceRepository.fetchAndInsertEnergyDataFromMock()
            _energyDataDetailLiveData.postValue(invoiceRepository.getAllEnergyData())
        }
    }
}