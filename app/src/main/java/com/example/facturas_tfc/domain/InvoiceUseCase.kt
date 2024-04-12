package com.example.facturas_tfc.domain

import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceRepository
import com.example.facturas_tfc.ui.fragments.model.InvoiceVO
import com.example.facturas_tfc.data.retrofit.network.reponse.asInvoiceVOList

class InvoiceUseCase {
   private val repository = InvoiceRepository()

    suspend operator fun invoke(): List<InvoiceVO>? {

        return repository.getDataFromAPI()?.asInvoiceVOList()


    }
}