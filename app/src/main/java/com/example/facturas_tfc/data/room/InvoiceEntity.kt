package com.example.facturas_tfc.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.facturas_tfc.ui.fragments.model.InvoiceVO


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

fun List<InvoiceEntity>.asListInvoicesVO(): List<InvoiceVO> {
    val entityList = this
    return entityList.map { entity -> entity.entityAsInvoiceVO() }
}

