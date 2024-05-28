package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.data.reponse.retrofit.InvoiceService
import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom
import com.example.facturas_tfc.data.reponse.room.InvoiceDatabase
import com.example.facturas_tfc.data.reponse.room.InvoiceEntity
import com.example.facturas_tfc.data.reponse.room.asListInvoicesVO
import com.example.facturas_tfc.model.InvoiceVO
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InvoiceRepository @Inject constructor(
    private val api: InvoiceService
){
    val energyDetailsDao = InvoiceDatabase.getAppDBInstance().getEnergyDetailsDataDAO()
    val invoiceDAO = InvoiceDatabase.getAppDBInstance().getAppDao()


    suspend fun getDataFromAPI(): List<InvoiceResponse>? {


        return api.getDataFromAPI()


    }
    suspend fun getDataFromKtor(): List<InvoiceResponse>? {
        return api.getDataFromKtor()
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

    suspend fun fetchAndInsertInvoicesFromKtor() {
        invoiceDAO.deleteAllInvoices()

        val invoicesFromKtor = getDataFromKtor()?.asInvoiceVOList() ?: emptyList()

        val invoicesRoom = invoicesFromKtor.map { invoice ->
            InvoiceEntity(
                status = invoice.status,
                amount = invoice.amount,
                date = invoice.date
            )
        }
        insertInvoices(invoicesRoom)

    }


    suspend fun insertEnergyDataDetails(energyDataModelRoom: EnergyDataModelRoom) {
        energyDetailsDao.insertInvoices(energyDataModelRoom)
    }

    fun getAllEnergyData(): EnergyDataModelRoom {
        return energyDetailsDao.getAllEnergyDataDetails()
    }


    suspend fun fetchAndInsertEnergyDataFromMock() {
        val energyDetails = api.getDataEnergyDetailsFromMock()
        val energyDetailsRoom = energyDetails?.asEnergyDataDetailsModelRoom()
        if (energyDetailsRoom != null) {
            insertEnergyDataDetails(energyDetailsRoom)
        }
    }


}