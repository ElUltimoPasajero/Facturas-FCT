package com.example.facturas_tfc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.model.InvoiceVO

class InvoiceAdapter(private val onClickListener: (InvoiceVO) -> Unit): RecyclerView.Adapter<InvoiceViewHolder>() {


        private var listInvoices: List<InvoiceVO>? = null



        fun setListInvoices(listInvoices: List<InvoiceVO>) {
            this.listInvoices = listInvoices
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            return InvoiceViewHolder(layoutInflater.inflate(R.layout.invoice_item, parent, false))
        }

        override fun getItemCount(): Int {
            if (listInvoices == null) return 0

            return listInvoices?.size!!
        }

        override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {

            holder.render(listInvoices?.get(position)!!, onClickListener)
        }
    }
