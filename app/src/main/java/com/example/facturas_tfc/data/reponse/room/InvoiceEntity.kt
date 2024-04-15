package com.example.facturas_tfc.data.reponse.room

import androidx.room.Entity
import com.example.facturas_tfc.model.InvoiceVO


@Entity(tableName = "invoice", primaryKeys = ["amount", "date"] //IMPORTANTE a la hora de pasar doble primary key
)
class InvoiceEntity(
    val status: String,
    val amount: Double,
    val date: String

) {
    fun entityAsInvoiceVO(): InvoiceVO {
        return InvoiceVO(
            status = status,
            date = date,
            amount = amount
        )
    }
}

fun List<InvoiceEntity>.asListInvoicesVO(): List<InvoiceVO> { //Metodo de extension para convertir una lista de invoiceEntity en una lista invoiceVO
    val entityList = this
    return entityList.map { entity -> entity.entityAsInvoiceVO() }
}

