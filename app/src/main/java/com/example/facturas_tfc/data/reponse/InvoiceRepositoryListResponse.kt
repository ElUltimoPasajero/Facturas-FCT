package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.model.InvoiceVO

data class InvoiceRepositoryListResponse(val numFacturas: Int, val facturas: List<InvoiceResponse>) {
}