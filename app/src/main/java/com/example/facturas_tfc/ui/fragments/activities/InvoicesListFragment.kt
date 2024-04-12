package com.example.facturas_tfc.ui.fragments.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.adapter.InvoiceAdapter
import com.example.facturas_tfc.databinding.FragmentInvoicesListBinding
import com.example.facturas_tfc.ui.fragments.viewmodel.InvoiceActivityViewmodel


class InvoicesListFragment : Fragment() {

    private lateinit var binding: FragmentInvoicesListBinding
    private lateinit var invoiceAdapter: InvoiceAdapter
    private val viewModel: InvoiceActivityViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchInvoices()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentInvoicesListBinding.inflate(layoutInflater, container, false)

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeInvoices()
        setOnClickListener()
        setItemDecoration()


    }

    private fun initRecyclerView() {
        invoiceAdapter = InvoiceAdapter { invoice ->
        }

        binding.rvInvoicesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = invoiceAdapter
        }
    }

    private fun observeInvoices() {
        viewModel.invoiceLiveData.observe(viewLifecycleOwner) { invoices ->
            invoiceAdapter.setListInvoices(invoices)
            invoiceAdapter.notifyDataSetChanged()
        }
    }

    private fun setItemDecoration() {
        val decoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)

        binding.rvInvoicesList.addItemDecoration(decoration)
    }


    private fun setOnClickListener() {

        binding.materialInvoicesToolbar.setOnMenuItemClickListener { //Asi se vincula un icono de menu con una toolbar de materialToolbar

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





