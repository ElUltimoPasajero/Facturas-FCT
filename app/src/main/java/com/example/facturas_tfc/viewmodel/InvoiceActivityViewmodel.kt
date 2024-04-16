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

    private var useAPI = true

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
                        true -> invoiceRepository.fetchandInsertInvoicesFromAPI()
                        false -> invoiceRepository.fetchandInsertInvoicesFromMock()
                    }

                    _invoiceLiveData.postValue(invoiceRepository.getAllInvoices())
                }

            } catch (e: Exception) {
                Log.e("InvoiceViewModel", "Error fetching invoices", e)
            }
        }
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


    fun swichtPosition(apiPosition: Boolean) {
        invoiceRepository.invoiceDAO.deleteAllInvoices()
        useAPI = apiPosition
        fetchInvoices()

    }
}
