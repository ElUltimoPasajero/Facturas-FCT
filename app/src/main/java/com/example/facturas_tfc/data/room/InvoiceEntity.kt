package com.example.facturas_tfc.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "invoice", primaryKeys = ["amount", "date"] //IMPORTANTE a la hora de pasar doble primary key
)
class InvoiceEntity(
    val status: String?,
    val amount: Double?,
    val date: String?
) {}