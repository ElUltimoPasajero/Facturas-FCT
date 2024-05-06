package com.example.facturas_tfc.data.reponse.retrofit

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockCircular
import co.infinum.retromock.meta.MockResponse
import co.infinum.retromock.meta.MockResponses
import com.example.facturas_tfc.data.reponse.EnergyDataDetail
import com.example.facturas_tfc.data.reponse.InvoiceRepositoryListResponse
import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom
import retrofit2.Response
import retrofit2.http.GET


interface InvoiceClientRetromock {
    @Mock
    @MockResponses(

        MockResponse(body = "mock.json"),
        MockResponse(body = "mock2.json")
    )
    @MockCircular
    @GET("resources")

    suspend fun getDataFromRetromock(): Response<InvoiceRepositoryListResponse>


}


interface InvoiceClient {


    @GET("facturas")
    suspend fun getDataFromAPI(): Response<InvoiceRepositoryListResponse>
}


interface EnergyDataRetroMock {
    @Mock
    @MockResponses(
        MockResponse(body = "mockEnergyData.json")
    )
    suspend fun getDataEnergyFromMock(): Response<EnergyDataDetail>
}