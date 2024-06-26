package com.example.facturas_tfc.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.InvoiceItemBinding
import com.example.facturas_tfc.model.InvoiceVO
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class InvoiceViewHolder(view: View): RecyclerView.ViewHolder(view) {


    val binding = InvoiceItemBinding.bind(view)


    fun render(item: InvoiceVO, onClickListener: (InvoiceVO) -> Unit) {

        binding.textViewStatus.text = item.status
        binding.textViewOrderAmmounr.text = "${item.amount.toString()} €"
        binding.textViewDate.text = formatDate(item.date)

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
