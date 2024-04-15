package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.model.InvoiceVO
import com.example.facturas_tfc.data.reponse.room.InvoiceEntity

data class InvoiceResponse(
    val descEstado: String,
    val fecha: String,
    val importeOrdenacion: Double
) {
    fun asInvoiceVO(): InvoiceVO {
        return InvoiceVO(
            status = descEstado,
            date = fecha,
            amount = importeOrdenacion
        )
    }



    fun asInvoiceEntity(): InvoiceEntity {
        return InvoiceEntity(

            status = descEstado,
            date = fecha,
            amount = importeOrdenacion
        )
    }
}

fun List<InvoiceResponse>.asInvoiceVOList(): List<InvoiceVO> {
    return this.map{ response -> response.asInvoiceVO() }
}