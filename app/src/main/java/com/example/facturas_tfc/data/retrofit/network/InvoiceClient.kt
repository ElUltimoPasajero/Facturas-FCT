package com.example.facturas_tfc.data.retrofit.network

import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceRepositoryListResponse
import retrofit2.Response
import retrofit2.http.GET

interface InvoiceClient {

    @GET("facturas")
    suspend fun getDataFromAPI(): Response<InvoiceRepositoryListResponse>
}