package com.example.facturas_tfc.data.reponse.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energyDataDetailsData")
class EnergyDataModelRoom (
    @PrimaryKey
    val cau: String,
    val requestStatus: String,
    val selfConsumptionType: String,
    val surplusCompensation: String,
    val installationPower: String
)