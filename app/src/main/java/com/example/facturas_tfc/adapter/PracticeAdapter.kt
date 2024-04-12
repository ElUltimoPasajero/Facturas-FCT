package com.example.facturas_tfc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.entities.PracticeVO

class PracticeAdapter(
    private val practiceList: List<PracticeVO>,
    private val onClickListener: (PracticeVO) -> Unit
) : RecyclerView.Adapter<PracticeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PracticeViewHolder(
            layoutInflater.inflate(
                R.layout.select_project_item,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
       return practiceList.size

    }

    override fun onBindViewHolder(holder: PracticeViewHolder, position: Int) {
        val item = practiceList[position]
        holder.render(item, onClickListener)

    }


}