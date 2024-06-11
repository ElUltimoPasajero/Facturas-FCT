package com.example.facturas_tfc.ui.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.facturas_tfc.MyApp
import com.example.facturas_tfc.R
import com.example.facturas_tfc.core.toDateString
import com.example.facturas_tfc.core.toLocalDate
import com.example.facturas_tfc.databinding.FragmentInvoicesFilterBinding
import com.example.facturas_tfc.viewmodel.InvoiceActivityViewmodel
import com.google.android.material.appbar.MaterialToolbar
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


class InvoicesFilterFragment : Fragment() {

    private lateinit var paid: CheckBox
    private lateinit var cancelled: CheckBox
    private lateinit var fixedPayment: CheckBox
    private lateinit var pendingPayment: CheckBox
    private lateinit var paymentPlan: CheckBox
    private lateinit var binding: FragmentInvoicesFilterBinding
    private var sliderValueAmmount = 0
    private val viewModel: InvoiceActivityViewmodel by activityViewModels()


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
        initSeekBar()
        initCheckBoxes()
        initResetButton()
        initApplyButton()
        saveSelectedFiltersFromFilterView()

    }

    fun buttonUntilInit() {


        binding.buttonUntil.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


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


        binding.buttonFrom.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
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
        val maxAmount = viewModel.maxAmountVm.toInt() + 1

        val currencyInstance = NumberFormat.getCurrencyInstance(Locale.GERMANY)

        val formattedMaxAmount = currencyInstance.format(maxAmount)
        binding.sliderAmmountSeekbar.max = maxAmount.toInt()
        binding.sliderCount.text = formattedMaxAmount
        binding.sliderMaxValor.text = formattedMaxAmount

        binding.sliderMinValor.text = currencyInstance.format(0.0)

        binding.sliderAmmountSeekbar.progress = maxAmount.toInt()



        binding.sliderAmmountSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val currencyInstance = NumberFormat.getCurrencyInstance(Locale.GERMANY)

                val formattedMaxAmount = currencyInstance.format(progress)
                sliderValueAmmount = progress
                binding.sliderCount.text = formattedMaxAmount
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }


    private fun initCheckBoxes() {
        paid = binding.checkBoxPaid
        cancelled = binding.checkBoxCancel
        fixedPayment = binding.checkBoxFixedPayment
        pendingPayment = binding.checkBoxPendingPayment
        paymentPlan = binding.checkBoxPayPlan


    }

    private fun initResetButton() {
        binding.buttonRestart.setOnClickListener {
            resetFilters()
        }
    }

    private fun initApplyButton() {

        binding.buttonApply.setOnClickListener {
            val maxValueSlider = binding.sliderAmmountSeekbar.progress

            val status = hashMapOf(
                paid.text.toString() to paid.isChecked,
                cancelled.text.toString() to cancelled.isChecked,
                fixedPayment.text.toString() to fixedPayment.isChecked,
                pendingPayment.text.toString() to pendingPayment.isChecked,
                paymentPlan.text.toString() to paymentPlan.isChecked
            )
            val dayMonthYearString = ContextCompat.getString(MyApp.context, R.string.day_month_year)

            val minDate: String = if (binding.buttonFrom.text == dayMonthYearString)
                LocalDate.ofEpochDay(0).toDateString("dd/MM/yyyy")
            else binding.buttonFrom.text.toString()

            val maxDate: String =
                if (binding.buttonUntil.text == dayMonthYearString) LocalDate.now()
                    .toDateString("dd/MM/yyyy") else binding.buttonUntil.text.toString()

            viewModel.applyFilters(maxDate, minDate, maxValueSlider.toDouble(), status)
            parentFragmentManager.popBackStack()
        }

    }


    private fun saveSelectedFiltersFromFilterView() {


        if (viewModel.filterLiveData.value != null) {
            binding.buttonFrom.text = viewModel.filterLiveData.value?.minDate
            binding.buttonUntil.text = viewModel.filterLiveData.value?.maxDate
            binding.sliderAmmountSeekbar.progress =
                viewModel.filterLiveData.value?.maxValueSlider?.toInt()!!
            binding.checkBoxPaid.isChecked =
                viewModel.filterLiveData.value?.status?.get("Pagada") ?: false
            binding.checkBoxCancel.isChecked =
                viewModel.filterLiveData.value?.status?.get("Cuota fija") ?: false
            binding.checkBoxFixedPayment.isChecked =
                viewModel.filterLiveData.value?.status?.get("Pendiente de pago") ?: false
            binding.checkBoxPendingPayment.isChecked =
                viewModel.filterLiveData.value?.status?.get("Pendiente de pago") ?: false
            binding.checkBoxPayPlan.isChecked =
                viewModel.filterLiveData.value?.status?.get("Plan de pago") ?: false
        }
    }


    private fun resetFilters() {

        binding.sliderAmmountSeekbar.progress = viewModel.maxAmountVm.toInt() + 1
        binding.buttonFrom.text = getString(R.string.day_month_year)
        binding.buttonUntil.text = getString(R.string.day_month_year)
        binding.checkBoxPaid.isChecked = false
        binding.checkBoxCancel.isChecked = false
        binding.checkBoxFixedPayment.isChecked = false
        binding.checkBoxPendingPayment.isChecked = false
        binding.checkBoxPayPlan.isChecked = false

    }
}
