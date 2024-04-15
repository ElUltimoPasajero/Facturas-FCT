package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.data.reponse.retrofit.InvoiceService
import com.example.facturas_tfc.data.reponse.room.InvoiceDatabase
import com.example.facturas_tfc.data.reponse.room.InvoiceEntity
import com.example.facturas_tfc.data.reponse.room.asListInvoicesVO
import com.example.facturas_tfc.model.InvoiceVO

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