package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.InvoiceRepository
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse

class InvoiceUseCase {
   private val repository = InvoiceRepository()

    suspend operator fun invoke(): List<InvoiceResponse>? {

        return repository.getDataFromAPI()


    }
}