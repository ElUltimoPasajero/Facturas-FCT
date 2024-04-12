package com.example.facturas_tfc.ui.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceRepository
import com.example.facturas_tfc.ui.fragments.model.InvoiceVO
import com.example.facturas_tfc.data.room.InvoiceEntity
import com.example.facturas_tfc.domain.InvoiceUseCase
import kotlinx.coroutines.launch

class InvoiceActivityViewmodel : ViewModel() {

    val invoiceUseCase = InvoiceUseCase()
    private val invoiceRepository = InvoiceRepository()
    private val _invoiceLiveData = MutableLiveData<List<InvoiceVO>>()
    val invoiceLiveData: LiveData<List<InvoiceVO>>
        get() = _invoiceLiveData

    init{
        viewModelScope.launch {
            // Save in livedata from room
            _invoiceLiveData.postValue(invoiceRepository.getAllInvoices())
        }
    }
    fun fetchInvoices() {
        viewModelScope.launch {
            try {
                val invoicesFromAPI = invoiceUseCase() ?: emptyList()

                // Convertir a InvoiceModelRoom y guardar en la base de datos Room
                val invoicesRoom = invoicesFromAPI.map { invoice ->
                    InvoiceEntity(
                        status = invoice.status,
                        amount = invoice.amount,
                        date = invoice.date
                    )
                }
                invoiceRepository.insertInvoices(invoicesRoom)
                // Save in livedata from retrofit
                _invoiceLiveData.postValue(invoiceRepository.getAllInvoices())
            } catch (e: Exception) {
                Log.e("InvoiceViewModel", "Error fetching invoices", e)
            }
        }
    }
}
