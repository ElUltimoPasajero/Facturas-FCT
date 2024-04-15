package com.example.facturas_tfc.data.reponse.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.facturas_tfc.MyApp

@Database(entities = [InvoiceEntity::class], version = 1, exportSchema = false)
 abstract class InvoiceDatabase: RoomDatabase() {


    abstract fun getAppDao(): InvoiceDAO
    companion object{
        private var DB_INSTANCE: InvoiceDatabase? = null


        fun getAppDBInstance(): InvoiceDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(MyApp.context, InvoiceDatabase::class.java, "invoice_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}