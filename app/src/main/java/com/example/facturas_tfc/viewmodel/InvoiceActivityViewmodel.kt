package com.example.facturas_tfc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse
import com.example.facturas_tfc.domain.InvoiceUseCase
import kotlinx.coroutines.launch

class InvoiceActivityViewmodel : ViewModel() {

    val invoiceUseCase = InvoiceUseCase()

    fun getInvoices(): MutableLiveData<List<InvoiceResponse>> {

        val invoicesLiveData = MutableLiveData<List<InvoiceResponse>>()

        viewModelScope.launch {

            try {
                val invoices = invoiceUseCase() ?: emptyList()
                invoicesLiveData.postValue(invoices)

            } catch (e: Exception) {
                Log.d("Error", "")
            }
        }
        return invoicesLiveData
    }
}