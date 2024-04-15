package com.example.facturas_tfc.data.reponse

data class InvoiceRepositoryListResponse(val numFacturas: Int, val facturas: List<InvoiceResponse>) {
}