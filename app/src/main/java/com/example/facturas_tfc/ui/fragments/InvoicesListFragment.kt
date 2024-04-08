package com.example.facturas_tfc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.FragmentInvoicesListBinding


class InvoicesListFragment : Fragment() {

    private lateinit var binding: FragmentInvoicesListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentInvoicesListBinding.inflate(layoutInflater, container, false)

        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.materialInvoicesToolbar.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.invoice_menu_filter -> {

                    val action =
                        InvoicesListFragmentDirections.actionInvoicesListFragmentToInvoicesFilterFragment()
                    findNavController().navigate(action)


                    true
                }


                else -> false
            }
        }
    }
}





