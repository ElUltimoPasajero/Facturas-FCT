package com.example.facturas_tfc.data.reponse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface InvoiceDAO {

    @Query("SELECT * FROM invoice")
    fun getallInvoices(): List<InvoiceEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvoices(invoiceEntity: List<InvoiceEntity>)

    @Query("DELETE FROM invoice")
    fun deleteAllInvoices()

}