package com.example.facturas_tfc.data.retrofit.network.reponse

data class InvoiceRepositoryListResponse(val numFacturas: Int, val facturas: List<InvoiceResponse>) {
}