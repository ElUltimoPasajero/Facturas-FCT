package com.example.facturas_tfc.data.retrofit.network

import android.util.Log
import com.example.facturas_tfc.core.network.RetrofitHelper
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class InvoiceService {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getDataFromAPI(): List<InvoiceResponse>? {
        val response = retrofit.create(InvoiceClient::class.java).getDataFromAPI()
        if (response.isSuccessful) {
            val invoices = response.body()?.facturas
            if (invoices.isNullOrEmpty()) {
                return emptyList()
            } else {
                return invoices
            }
        } else {
            Log.d("Error", response.toString())
            return null

        }

    }
}




