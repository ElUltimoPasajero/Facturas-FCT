package com.example.facturas_tfc.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.databinding.SelectProjectItemBinding
import com.example.facturas_tfc.entities.PracticeVO

class PracticeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = SelectProjectItemBinding.bind(view)


    fun render(item: PracticeVO, onClickListener: (PracticeVO) -> Unit) {


        binding.tvProject.text = item.name

        itemView.setOnClickListener {
            onClickListener(item)
        }

    }
}
