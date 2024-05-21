package com.example.facturas_tfc.data.network.retromock

import co.infinum.retromock.Retromock
import retrofit2.Retrofit
object RetromockHelper {

        fun getRetromock(retrofit: Retrofit): Retromock {
            return Retromock.Builder()
                .retrofit(retrofit)
                .defaultBodyFactory(ResourceBodyFactory())
                .build()
        }

}
