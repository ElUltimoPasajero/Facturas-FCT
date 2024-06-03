package com.example.facturas_tfc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SmartSolarEnergyActivityViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository
) : ViewModel() {

    private val _energyDataDetailLiveData = MutableLiveData<EnergyDataModelRoom>()
    val energyDataDetailsLiveData: LiveData<EnergyDataModelRoom>
        get() = _energyDataDetailLiveData

    init {

        initRepository()
        fetchEnergyDataDetails()

    }

    private fun initRepository() {
    }

     fun fetchEnergyDataDetails() {
        viewModelScope.launch {
            invoiceRepository.fetchAndInsertEnergyDataFromMock()
            _energyDataDetailLiveData.postValue(invoiceRepository.getAllEnergyData())
        }
    }

}