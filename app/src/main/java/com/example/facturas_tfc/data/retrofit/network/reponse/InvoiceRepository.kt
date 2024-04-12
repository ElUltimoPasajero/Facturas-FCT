package com.example.facturas_tfc.data.retrofit.network.reponse

import com.example.facturas_tfc.data.retrofit.network.InvoiceService
import com.example.facturas_tfc.data.room.InvoiceDatabase
import com.example.facturas_tfc.data.room.InvoiceEntity
import com.example.facturas_tfc.data.room.asListInvoicesVO
import com.example.facturas_tfc.ui.fragments.model.InvoiceVO

class InvoiceRepository {
    val api = InvoiceService()
    val invoiceDAO = InvoiceDatabase.getAppDBInstance().getAppDao()


    suspend fun getDataFromAPI(): List<InvoiceResponse>? {


        return api.getDataFromAPI()


    }

    suspend fun insertInvoices(invoices: List<InvoiceEntity>) {
        invoiceDAO.insertInvoices(invoices)
    }

    fun getAllInvoices(): List<InvoiceVO> {
        val invoicesEntity: List<InvoiceEntity> = invoiceDAO.getallInvoices()
        return invoicesEntity.asListInvoicesVO()
    }
}