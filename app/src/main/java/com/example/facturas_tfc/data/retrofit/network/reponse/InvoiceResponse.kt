package com.example.facturas_tfc.data.retrofit.network.reponse

data class InvoiceResponse(
    val descEstado: String,
    val fecha: String,
    val importeOrdenacion: Double
) {
}