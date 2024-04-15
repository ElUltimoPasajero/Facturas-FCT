package com.example.facturas_tfc.model

import androidx.room.Entity


data class InvoiceVO(
    val status: String,
    val date: String,
    val amount: Double
)
