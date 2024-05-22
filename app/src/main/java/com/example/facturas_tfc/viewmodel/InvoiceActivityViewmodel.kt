package com.example.facturas_tfc.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facturas_tfc.MyApp
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.model.FilterInvoiceVO
import com.example.facturas_tfc.model.InvoiceVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class InvoiceActivityViewmodel @Inject constructor(private val invoiceRepository: InvoiceRepository
): ViewModel() {

    private val _invoiceLiveData = MutableLiveData<List<InvoiceVO>>()
    private var _maxAmount: Double = 0.0
    private var invoicesList: List<InvoiceVO> = emptyList()
    private val _filteredInvoicesListLiveData = MutableLiveData<List<InvoiceVO>>()


    val filteredInvoicesListLiveData: LiveData<List<InvoiceVO>>
        get() = _filteredInvoicesListLiveData


    private var _filterLiveData = MutableLiveData<FilterInvoiceVO>()
    val filterLiveData: LiveData<FilterInvoiceVO>
        get() = _filterLiveData


    val invoiceListLiveData: LiveData<List<InvoiceVO>>
        get() = _invoiceLiveData


    var maxAmountVm = 0.0
        get() = _maxAmount


    private var useAPI = false


    init {
        fetchInvoices()
    }


    fun fetchInvoices() {
        viewModelScope.launch {

            _filteredInvoicesListLiveData.postValue(invoiceRepository.getAllInvoices())

            try {
                if (isInternetAvailable()) {

                    when (useAPI) {
                        true -> invoiceRepository.fetchandInsertInvoicesFromAPI()
                        false -> invoiceRepository.fetchandInsertInvoicesFromMock()
                    }
                    invoicesList = invoiceRepository.getAllInvoices()
                    getMaxAmmountFromInvoices()
                    _filteredInvoicesListLiveData.postValue(invoicesList)


                }

            } catch (e: Exception) {
                Log.e("InvoiceViewModel", "Error fetching invoices", e)
            }
        }
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }


    fun swichtPosition(apiPosition: Boolean) {
        invoiceRepository.invoiceDAO.deleteAllInvoices()
        useAPI = apiPosition
        fetchInvoices()

    }


    fun getMaxAmmountFromInvoices() {
        var max = 0.0

        for (invoice in invoicesList) {
            val actualInvoiceAmount = invoice.amount
            if (max < actualInvoiceAmount) {
                max = actualInvoiceAmount
            }
        }
        _maxAmount = max
    }


    fun applyFilters(
        buttonFromValue: String,
        buttonUntilValue: String,
        maxValueSlider: Double,
        status: HashMap<String, Boolean>
    ) {

        val filter = FilterInvoiceVO(buttonFromValue, buttonUntilValue, maxValueSlider, status)
        _filterLiveData.postValue(filter)


    }

    fun verifyFilters() {
        var filteredList = verifyDateFilter()
        filteredList = verifyCheckBox(filteredList)
        filteredList = verifySeekBar(filteredList)
        _filteredInvoicesListLiveData.postValue(filteredList)
    }


    private fun verifySeekBar(filteredList: List<InvoiceVO>): List<InvoiceVO> {

        var filteredInvoicesVySeekBar = ArrayList<InvoiceVO>()
        val maxValueSlider = filterLiveData.value?.maxValueSlider
        for (invoice in filteredList) {
            if (invoice.amount!! < maxValueSlider!!) {
                filteredInvoicesVySeekBar.add(invoice)
            }
        }
        return filteredInvoicesVySeekBar
    }


    private fun verifyCheckBox(
        filteredInvoices: List<InvoiceVO>?
    ): List<InvoiceVO> {
        var filteredInvoicesCheckBox = ArrayList<InvoiceVO>()
        val status = filterLiveData.value?.status
        //Se obtienen los estados de las CheckBoxes.

        val checkBoxPaid = status?.get("Pagada") ?: false
        val checkBoxCanceled = status?.get("Anulada") ?: false
        val checkBoxFixedPayment = status?.get("Cuota fija") ?: false
        val checkBoxPendingPayment = status?.get("Pendiente de pago") ?: false
        val checkBoxPaymentPlan = status?.get("Plan de pago") ?: false


        if (checkBoxPaid || checkBoxCanceled || checkBoxFixedPayment || checkBoxPendingPayment || checkBoxPaymentPlan) {

            for (invoice in filteredInvoices ?: emptyList()) {
                val invoiceState = invoice.status
                val isPaid = invoiceState == "Pagada"
                val isCanceled = invoiceState == "Anuladas"
                val isFixedPayment = invoiceState == "cuotaFija"
                val isPendingPayment = invoiceState == "Pendiente de pago"
                val isPaymentPlan = invoiceState == "planPago"

                if ((isPaid && checkBoxPaid) || (isCanceled && checkBoxCanceled) || (isFixedPayment && checkBoxFixedPayment) || (isPendingPayment && checkBoxPendingPayment) || (isPaymentPlan && checkBoxPaymentPlan)) {
                    filteredInvoicesCheckBox.add(invoice)
                }
            }
            return filteredInvoicesCheckBox
        } else {
            return filteredInvoices ?: emptyList()
        }
    }


    private fun verifyDateFilter(): List<InvoiceVO> {
        val maxDate = filterLiveData.value?.maxDate
        val minDate = filterLiveData.value?.minDate
        val filteredList = ArrayList<InvoiceVO>()


        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        var minDateLocal: Date? = null
        var maxDateLocal: Date? = null

        try {
            minDateLocal = minDate?.let { simpleDateFormat.parse(it) }
            maxDateLocal = maxDate?.let { simpleDateFormat.parse(it) }
        } catch (e: ParseException) {
            Log.d("ERROR ", "DateParseError")
        }
        for (invoice in invoicesList) {
            var invoiceDate = Date()
            try {
                invoiceDate = simpleDateFormat.parse(invoice.date)!!
            } catch (e: ParseException) {
                Log.d("ERROR", "DatParseError")
            }
            if (invoiceDate.after(minDateLocal) && invoiceDate.before(maxDateLocal)) {
                filteredList.add(invoice)
            }
        }

        return filteredList
    }

}




