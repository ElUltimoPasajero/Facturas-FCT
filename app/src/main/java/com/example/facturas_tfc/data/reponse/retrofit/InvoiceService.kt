package com.example.facturas_tfc.data.reponse.retrofit

import android.util.Log
import com.example.facturas_tfc.data.reponse.InvoiceClient
import com.example.facturas_tfc.data.network.RetrofitHelper
import com.example.facturas_tfc.data.network.retromock.RetromockHelper
import com.example.facturas_tfc.data.reponse.InvoiceClientRetromock
import com.example.facturas_tfc.data.reponse.InvoiceResponse


class InvoiceService {

    private val retrofitBuilder = RetrofitHelper.getRetrofit()
    private val retromockBuilder = RetromockHelper.getRetromock(retrofitBuilder)
    val retromock = retromockBuilder.create(InvoiceClientRetromock::class.java)
    val retrofit = retrofitBuilder.create(InvoiceClient::class.java)

    suspend fun getDataFromAPI(): List<InvoiceResponse>? {
        val response = retrofit.getDataFromAPI()
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

        suspend fun getDataFromRetromock(): List<InvoiceResponse>? {
        val response = retromock.getDataFromRetromock()
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




