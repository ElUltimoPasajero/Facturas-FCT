package com.example.facturas_tfc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.data.model.InvoiceVO
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse
import com.example.facturas_tfc.domain.InvoiceUseCase
import kotlinx.coroutines.launch

class InvoiceActivityViewmodel : ViewModel() {

    val invoiceUseCase = InvoiceUseCase()

    private val _invoiceLiveData =  MutableLiveData<List<InvoiceVO>>()
    val invoiceLiveData: LiveData<List<InvoiceVO>>
        get() = _invoiceLiveData

    fun fetchInvoices() {
        Log.d("FACTURAS", "fetching")

        viewModelScope.launch {

            try {
                val invoices = invoiceUseCase() ?: emptyList()
                _invoiceLiveData.postValue(invoices)

            } catch (e: Exception) {
                Log.d("Error", "")
            }
        }
    }
}