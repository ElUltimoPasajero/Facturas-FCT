package com.example.facturas_tfc.data.reponse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface EnergyDataDetailsDAO {


    @Query("SELECT * FROM energyDataDetailsData")
    fun getAllEnergyDataDetails(): EnergyDataModelRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvoices(energyDataModelRoom: EnergyDataModelRoom)

    @Query("DELETE FROM energyDataDetailsData")
    fun deleteAllEnergyDatDetails()
}