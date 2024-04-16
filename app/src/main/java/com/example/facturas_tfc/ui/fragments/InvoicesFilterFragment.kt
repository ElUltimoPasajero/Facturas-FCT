package com.example.facturas_tfc.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.facturas_tfc.R
import com.example.facturas_tfc.databinding.FragmentInvoicesFilterBinding
import com.example.facturas_tfc.databinding.FragmentInvoicesListBinding
import com.google.android.material.appbar.MaterialToolbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class InvoicesFilterFragment : Fragment() {

    private lateinit var binding: FragmentInvoicesFilterBinding
    private var maxAmount: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInvoicesFilterBinding.inflate(inflater, container, false)
        return binding.root






    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar: MaterialToolbar = binding.materialToolbar
        toolbar.setNavigationOnClickListener {
            Log.d("CLICK", "")
            parentFragmentManager.popBackStack()
        }

        initCalendar()
    }
    fun buttonUntilInit() {
        binding.buttonUntil.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Crear un DatePickerDialog con la fecha actual como predeterminada

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year1, month1, dayOfMonth ->
                    binding.buttonUntil.text = "$dayOfMonth/${month1 + 1}/$year1"
                },
                year,
                month,
                day
            )
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateFromLocale = binding.buttonFrom.text.toString()
            val dateFrom: Date
            try {
                dateFrom = simpleDateFormat.parse(dateFromLocale)
                datePickerDialog.datePicker.minDate = dateFrom.time
            } catch (e: ParseException) {

                e.printStackTrace()
            }
            datePickerDialog.show()
        }
    }


    private fun ButtonFromInit() {

        // Establecer un escuchador de clics para el botón "From"

        binding.buttonFrom.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            // Crear un DatePickerDialog con la fecha actual como predeterminada
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year1, month1, dayOfMonth ->
                    binding.buttonFrom.text = "$dayOfMonth/${month1 + 1}/$year1"
                },
                year,
                month,
                day
            )
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateUntilLocale = binding.buttonUntil.text.toString()
            val dateUntil: Date
            try {
                dateUntil = simpleDateFormat.parse(dateUntilLocale)
                datePickerDialog.datePicker.maxDate = dateUntil.time
            } catch (e: ParseException) {

                e.printStackTrace()
            }
            datePickerDialog.show()
        }
    }

     fun initCalendar() {

        ButtonFromInit()
        buttonUntilInit()

    }
    fun initSeekBar() {

        binding.sliderAmmount.max = maxAmount
        binding.sliderCount.text = "${maxAmount}"
        binding.sliderMaxValor.text = "${maxAmount}€"
        binding.sliderMinValor.text = "0€"
        binding.sliderAmmount.progress = maxAmount


        binding.sliderAmmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.sliderCount.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //
            }
        })

    }


}
