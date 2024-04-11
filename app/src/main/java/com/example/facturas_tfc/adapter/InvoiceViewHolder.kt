package com.example.facturas_tfc.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.data.retrofit.network.reponse.InvoiceResponse
import com.example.facturas_tfc.databinding.FragmentInvoicesListBinding
import com.example.facturas_tfc.databinding.InvoiceItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class InvoiceViewHolder(view: View): RecyclerView.ViewHolder(view) {


    val binding = InvoiceItemBinding.bind(view)


    fun render(item: InvoiceResponse, onClickListener: (InvoiceResponse) -> Unit) {

        binding.textViewStatus.text = item.descEstado
        binding.textViewOrderAmmounr.text = "${item.importeOrdenacion.toString()} €"
        binding.textViewDate.text = item.fecha?.let { formatDate(it) }

        itemView.setOnClickListener {
            onClickListener(item)
        }



        if (binding.textViewStatus.text.equals("Pendiente de pago")) {
            val notPaidInvoice = ContextCompat.getColor(itemView.context, R.color.red)
            binding.textViewStatus.setTextColor(notPaidInvoice)
        } else {
            val paidInvoice = ContextCompat.getColor(itemView.context, R.color.greenProject)
            binding.textViewStatus.setText("")
        }
    }

    fun formatDate(date: String): String {
        try {
            val insert = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val format = insert.parse(date)
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale("es", "ES"))

            return format?.let { outputFormat.format(it) } ?: date


        } catch (e: ParseException) {
            e.printStackTrace()
            return date
        }


    }
}
