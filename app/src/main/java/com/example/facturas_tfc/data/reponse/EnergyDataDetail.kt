package com.example.facturas_tfc.data.reponse

import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom

data class EnergyDataDetail(
    val cau: String,
    val requestStatus: String,
    val selfConsumptionType: String,
    val surplusCompensation: String,
    val installationPower: String
) {


    fun asEnergyDataDetailsModelRoom(): EnergyDataModelRoom {
        return EnergyDataModelRoom(cau, requestStatus, selfConsumptionType, surplusCompensation, installationPower)
    }
}
