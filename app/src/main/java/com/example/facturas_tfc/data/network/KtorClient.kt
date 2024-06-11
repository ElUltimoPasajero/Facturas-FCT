package com.example.facturas_tfc.data.network

import android.util.Log
import com.example.facturas_tfc.data.reponse.InvoiceRepositoryListResponse
import com.example.facturas_tfc.data.reponse.InvoiceResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun getDataFromKtor(): List<InvoiceResponse>? {
        return try {
            val invoices = client.get("https://viewnextandroid4.wiremockapi.cloud/facturas").body<InvoiceRepositoryListResponse>().facturas
            invoices
        } catch (e: Exception) {
            Log.e("Error", "Failed to fetch data from Ktor: ${e.message}")
            null
        }
    }
}