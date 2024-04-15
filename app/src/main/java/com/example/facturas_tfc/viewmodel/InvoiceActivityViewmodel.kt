package com.example.facturas_tfc.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.MyApp
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.model.InvoiceVO
import com.example.facturas_tfc.data.reponse.room.InvoiceEntity
import com.example.facturas_tfc.domain.InvoiceUseCase
import kotlinx.coroutines.launch

class InvoiceActivityViewmodel : ViewModel() {

    val invoiceUseCase = InvoiceUseCase()
    private val invoiceRepository = InvoiceRepository()
    private val _invoiceLiveData = MutableLiveData<List<InvoiceVO>>()
    val invoiceLiveData: LiveData<List<InvoiceVO>>
        get() = _invoiceLiveData

    private val useAPI = true

    init {
        viewModelScope.launch {
            // Save in livedata from room
            _invoiceLiveData.postValue(invoiceRepository.getAllInvoices())
        }
    }

    fun fetchInvoices() {
        viewModelScope.launch {
            try {
                if (isInternetAvailable()) {

                    when (useAPI) {
                        true -> fetchandInsertInvoicesFromAPI()
                        false -> fetchandInsertInvoicesFromMock()
                    }
                }

                _invoiceLiveData.postValue(invoiceRepository.getAllInvoices())
            } catch (e: Exception) {
                Log.e("InvoiceViewModel", "Error fetching invoices", e)
            }
        }
    }

    private suspend fun InvoiceActivityViewmodel.fetchandInsertInvoicesFromAPI() {
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
    }


    private suspend fun InvoiceActivityViewmodel.fetchandInsertInvoicesFromMock() {
        val invoicesFromMock = invoiceUseCase() ?: emptyList()

        val invoicesRoom = invoicesFromMock.map { invoice ->
            InvoiceEntity(
                status = invoice.status,
                amount = invoice.amount,
                date = invoice.date
            )
        }
        invoiceRepository.insertInvoices(invoicesRoom)
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }
}
