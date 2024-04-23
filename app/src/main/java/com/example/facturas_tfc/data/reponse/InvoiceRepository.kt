package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.data.reponse.retrofit.InvoiceService
import com.example.facturas_tfc.data.reponse.room.InvoiceDatabase
import com.example.facturas_tfc.data.reponse.room.InvoiceEntity
import com.example.facturas_tfc.data.reponse.room.asListInvoicesVO
import com.example.facturas_tfc.model.InvoiceVO
import com.example.facturas_tfc.viewmodel.InvoiceActivityViewmodel

class InvoiceRepository {
    val api = InvoiceService()
    val invoiceDAO = InvoiceDatabase.getAppDBInstance().getAppDao()


    suspend fun getDataFromAPI(): List<InvoiceResponse>? {


        return api.getDataFromAPI()


    }

    suspend fun getDataFromMock(): List<InvoiceResponse>? {


        return api.getDataFromRetromock()


    }

    suspend fun insertInvoices(invoices: List<InvoiceEntity>) {
        invoiceDAO.insertInvoices(invoices)
    }

    fun getAllInvoices(): List<InvoiceVO> {
        val invoicesEntity: List<InvoiceEntity> = invoiceDAO.getallInvoices()
        return invoicesEntity.asListInvoicesVO()
    }

     suspend fun fetchandInsertInvoicesFromMock() {
        val invoicesFromMock = getDataFromMock()?.asInvoiceVOList() ?: emptyList()

        val invoicesRoom = invoicesFromMock.map { invoice ->
            InvoiceEntity(
                status = invoice.status,
                amount = invoice.amount,
                date = invoice.date
            )
        }
      insertInvoices(invoicesRoom)
    }

     suspend fun fetchandInsertInvoicesFromAPI() {
        val invoicesFromAPI = getDataFromAPI()?.asInvoiceVOList() ?: emptyList()

        val invoicesRoom = invoicesFromAPI.map { invoice ->
            InvoiceEntity(
                status = invoice.status,
                amount = invoice.amount,
                date = invoice.date
            )
        }
        insertInvoices(invoicesRoom)
    }


}