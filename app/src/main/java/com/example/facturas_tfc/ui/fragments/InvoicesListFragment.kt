package com.example.facturas_tfc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facturas_tfc.R
import com.example.facturas_tfc.adapter.InvoiceAdapter
import com.example.facturas_tfc.databinding.FragmentInvoicesListBinding
import com.example.facturas_tfc.ui.activities.MainActivity
import com.example.facturas_tfc.viewmodel.InvoiceActivityViewmodel
import com.example.facturas_tfc.viewmodel.RemoteConfigViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvoicesListFragment : Fragment() {

    private lateinit var binding: FragmentInvoicesListBinding
    private lateinit var invoiceAdapter: InvoiceAdapter
    private val viewModel: InvoiceActivityViewmodel by activityViewModels()
    private val remoteConfigViewModel: RemoteConfigViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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



        binding.buttonRetrofit.setOnClickListener {
            viewModel.swichtPosition(true)
            Toast.makeText(requireContext(), "Using Retrofit", Toast.LENGTH_SHORT).show()
        }

        binding.buttonRetromock.setOnClickListener {
            viewModel.swichtPosition(false)
            Toast.makeText(requireContext(), "Using Retromock", Toast.LENGTH_SHORT).show()
        }


        remoteConfigViewModel.switchVisibility.observe(viewLifecycleOwner) { visible ->
            if (visible) {
                binding.retroMockWsitch.visibility = View.VISIBLE
            } else {
                binding.retroMockWsitch.visibility = View.GONE
                binding.retroMockText.visibility=View.GONE
            }
        }

        remoteConfigViewModel.changeAppTheme.observe(viewLifecycleOwner) { darkThemeEnabled ->
            remoteConfigViewModel.applyTheme(darkThemeEnabled)
        }



        binding.retroMockWsitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.swichtPosition(isChecked)
            Toast.makeText(
                requireContext(),
                "Using ${if (isChecked) "Retrofit" else "Retromock"}",
                Toast.LENGTH_SHORT
            ).show()


        }

    }

    private fun initRecyclerView() {
        invoiceAdapter = InvoiceAdapter { invoice ->
            showAlertDialog()


        }

        binding.rvInvoicesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = invoiceAdapter
        }
    }

    private fun observeInvoices() {
        viewModel.filteredInvoicesListLiveData.observe(viewLifecycleOwner) { invoices ->
            invoiceAdapter.setListInvoices(invoices)
            viewModel.getMaxAmmountFromInvoices()
            invoiceAdapter.notifyDataSetChanged()
        }
        viewModel.filterLiveData.observe(viewLifecycleOwner) { filter ->
            if (filter != null) {
                viewModel.verifyFilters()
            }
        }
    }

    private fun setItemDecoration() {
        val decoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)

        binding.rvInvoicesList.addItemDecoration(decoration)
    }


    private fun setOnClickListener() {

        binding.materialInvoicesToolbar.setNavigationOnClickListener {
            startActivity(MainActivity.create(requireContext()))
        }


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
    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Funcionalidad no implementada")
            setMessage("Lo siento, esta funcionalidad aún no está disponible.")
            setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}





