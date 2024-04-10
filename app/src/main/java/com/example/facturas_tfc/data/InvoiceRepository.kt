package com.example.facturas_tfc.data

import com.example.facturas_tfc.data.retrofit.network.InvoiceService
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse

class InvoiceRepository {
    val api = InvoiceService()

    suspend fun getDataFromAPI(): List<InvoiceResponse>? {

        return api.getDataFromAPI()


    }
}